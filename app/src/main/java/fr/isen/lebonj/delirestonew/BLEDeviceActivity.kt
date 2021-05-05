package fr.isen.lebonj.delirestonew

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lebonj.delirestonew.databinding.ActivityBLEDeviceBinding

class BLEDeviceActivity : AppCompatActivity() {

    var bluetoothGatt: BluetoothGatt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityBLEDeviceBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val device = intent.getParcelableExtra<BluetoothDevice>("ble_device")
        binding.nameDeviceBLE.text = device?.name?:"Appareil inconnu"
        binding.StatusBLE.text = getString(R.string.ble_device_status, getString(R.string.ble_device_status_connecting))

        connectToDevice(device)
        binding.adressBleDetail.text = device?.address
    }

    private fun connectToDevice(device: BluetoothDevice?)
    {
        var bluetoothGatt = device?.connectGatt(this, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                super.onConnectionStateChange(gatt, status, newState)
                connectionStateChange(newState, gatt)

            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                super.onServicesDiscovered(gatt, status)
                gatt.services?.let{

                }

            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                super.onCharacteristicRead(gatt, characteristic, status)
            }


        })

    }

    private fun connectionStateChange(newState: Int, gatt: BluetoothGatt?)
    {
        BLEConnexionState.getBLEConnexionStateFromState(newState)?.let {
            runOnUiThread{
                //binding.deviceStatus = getString(R.string.ble_device_status, getString(it.text))
            }
            if(it.state == BLEConnexionState.STATE_CONNECTED.state)
            {
                gatt?.discoverServices()
            }
        }
        }

    }
