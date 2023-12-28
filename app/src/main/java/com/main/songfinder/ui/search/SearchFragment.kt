package com.main.songfinder.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.songfinder.R
import kotlin.math.sqrt
/**
 * Questa classe fornisce il fragment  della main activity
 * @author umbertodomenicociccias
 * */
class SearchFragment : Fragment(), ShakeDetector.OnShakeListener {

    private val viewModel by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java]
    }

    private lateinit var adapter: SearchAdapter
    private lateinit var sensorManager: SensorManager
    private lateinit var shakeDetector: ShakeDetector
    private lateinit var accelerometer: Sensor
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometerSensor != null) {
            accelerometer = accelerometerSensor
            shakeDetector = ShakeDetector(this)
        }
        val recyclerView: RecyclerView = this.requireView().findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = SearchAdapter(this, viewModel.responseList)
        recyclerView.adapter = adapter
        val searchResponseEdit: EditText = this.requireView().findViewById(R.id.searchResponseEdit)

        searchResponseEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchResponses(content)
            } else {
                recyclerView.visibility = View.GONE
                viewModel.responseList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.searchResponseLiveData.observe(viewLifecycleOwner) { result ->
            val search = result.getOrNull()
            if (search != null) {
                recyclerView.visibility = View.VISIBLE
                viewModel.responseList.clear()
                viewModel.responseList.addAll(search.response.hits)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Cannot find any result Check Internet Connection", Toast.LENGTH_LONG).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(shakeDetector)
        Log.d("SearchFragment", "Sensor rilasciato")
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            shakeDetector,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        Log.d("SearchFragment", "Sensor riacquisito")
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(shakeDetector)
        Log.d("SearchFragment", "Sensor rilasciato")
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onShake() {
        val searchResponseEdit: EditText = this.requireView().findViewById(R.id.searchResponseEdit)
        if (searchResponseEdit.text.isNullOrEmpty())
            return
        val intent = Intent(requireContext(), UndoActivity::class.java)
        val requestCode = 0
        startActivityForResult(intent, requestCode)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val risultato = data?.getIntExtra("undo_result", -1)
        if (requestCode == 0 && risultato != -1 && resultCode == Activity.RESULT_OK) {
            val searchResponseEdit: EditText =
                this.requireView().findViewById(R.id.searchResponseEdit)
            searchResponseEdit.setText("")
        }
    }
}

/**
 * Questa classe fornisce si occupa di implementare l'evento shake
 * @author umbertodomenicociccias
 * */
class ShakeDetector(private val listener: OnShakeListener) : SensorEventListener {

    private var lastUpdate: Long = 0
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f

    private val shakeThreshold = 800

    interface OnShakeListener {
        fun onShake()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        val currentTime = System.currentTimeMillis()
        val timeDifference = currentTime - lastUpdate

        if (timeDifference > 100) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val deltaX = x - lastX
            val deltaY = y - lastY
            val deltaZ = z - lastZ

            val speed =
                sqrt((deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ).toDouble()) / timeDifference * 10000

            if (speed > shakeThreshold) {
                listener.onShake()
            }

            lastUpdate = currentTime
            lastX = x
            lastY = y
            lastZ = z
        }
    }
}