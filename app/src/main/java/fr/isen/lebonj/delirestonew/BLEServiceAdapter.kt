package fr.isen.lebonj.delirestonew

import android.bluetooth.BluetoothGattCharacteristic
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import fr.isen.lebonj.delirestonew.modele.BLEService

class BLEServiceAdapter(private val serviceList: MutableList<BLEService>):ExpandableRecyclerViewAdapter <BLEServiceAdapter.ServiceViewHolder, BLEServiceAdapter.CharacteristicViewHolder> (serviceList) {


    class ServiceViewHolder(itemView: View): GroupViewHolder(itemView)
    {
        val serviceName = itemView.findViewById<TextView>(R.id.serviceName)
        private val serviceArrow = itemView.findViewById<TextView>(R.id.serviceName)

        override fun expand() {
            super.expand()
            serviceArrow.animate().rotation(-180f).setDuration(400L).start()
        }


        override fun collapse() {
            super.collapse()
            serviceArrow.animate().rotation(0f).setDuration(400L).start()
        }
    }


    class CharacteristicViewHolder(itemView: View): ChildViewHolder(itemView)
    {
        val characteristicName = itemView.findViewById<TextView>(R.id.characteristicName)
        val characteristicRead = itemView.findViewById<TextView>(R.id.readAction)
        val characteristicWrite = itemView.findViewById<TextView>(R.id.writeAction)
        val characteristicNotify = itemView.findViewById<TextView>(R.id.notifAction)

    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_b_l_e_device_service_cell,parent, false)
        return ServiceViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): CharacteristicViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.activity_b_l_e_device_characteristic_cell,parent, false)
        return CharacteristicViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: CharacteristicViewHolder, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int) {
       holder.characteristicName.text = (group.items[childIndex] as BluetoothGattCharacteristic).uuid.toString()
    }

    override fun onBindGroupViewHolder(holder: ServiceViewHolder, flatPosition: Int, group: ExpandableGroup<*>) {
        holder.serviceName.text = group.title
    }

}