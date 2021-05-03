package fr.isen.lebonj.delirestonew

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lebonj.delirestonew.databinding.CellBleDeviceBinding

class BleScanAdapter(private val listBLE: MutableList<ScanResult>,
                     private val clickListener: (ScanResult) -> Unit) : RecyclerView.Adapter<BleScanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellBleDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(binding: CellBleDeviceBinding) : RecyclerView.ViewHolder(binding.root) {
        val layout = binding.root
        val BleName: TextView = itemView.findViewById(R.id.bleName)
        val BleAdresse: TextView = itemView.findViewById(R.id.bleAdresse)
        val numberID: TextView = itemView.findViewById(R.id.numberID)
        val PowerBle: TextView = itemView.findViewById(R.id.powerBle)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.BleName.text=listBLE[position].scanRecord?.deviceName.toString()
        holder.BleAdresse.text= "MAC : " +listBLE[position].device.address.toString()
        holder.numberID.text =listBLE[position].scanRecord?.advertiseFlags.toString()

        if(listBLE[position].scanRecord?.txPowerLevel !=null)
        {
            holder.PowerBle.text = "Puissance :"+listBLE[position].scanRecord?.txPowerLevel.toString()
        }

        holder.layout.setOnClickListener {
            clickListener.invoke(listBLE[position])
        }

    }

    fun addDevice(AppareilData: ScanResult) {
        if (!listBLE.contains(AppareilData)) {
            listBLE.add(AppareilData)

        }
    }

    override fun getItemCount(): Int = listBLE.size
}