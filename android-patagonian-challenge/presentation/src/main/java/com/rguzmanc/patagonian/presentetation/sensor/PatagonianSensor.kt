package com.rguzmanc.patagonian.presentetation.sensor

import android.content.Context
import android.hardware.Sensor.TYPE_GYROSCOPE
import android.hardware.SensorManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.kircherelectronics.fsensor.filter.averaging.MeanFilter
import com.kircherelectronics.fsensor.observer.SensorSubject
import com.kircherelectronics.fsensor.sensor.FSensor
import com.kircherelectronics.fsensor.sensor.gyroscope.ComplementaryGyroscopeSensor
import timber.log.Timber


interface PatagonianSensor: LifecycleObserver{

    interface Listener {
        fun onDataChange(dz: Float)
    }

    fun isAvailable(): Boolean

    fun registerListener()

    fun unRegisterListener()

    var listener: Listener?
}

class DefaultPatagonianSensor(private val context: Context) : PatagonianSensor {

    companion object {
        private const val TAG = "DefaultPatagonianSensor"
        private const val FILTER_TIME_CONSTANT = 2.0f
        private const val IMU_OCF_QUATERNION_COEFF = 0.5f
    }

    override var listener: PatagonianSensor.Listener? = null

    private var fSensor: FSensor? = null
    private var meanFilter: MeanFilter? = null
    private var sensorObserver = SensorSubject.SensorObserver { values ->
        val filterValues = meanFilter?.filter(values)?.toList().orEmpty()
        listener?.onDataChange(filterValues[0])
    }

    init {

        fSensor = ComplementaryGyroscopeSensor(context).apply {
            setFSensorComplimentaryTimeConstant(IMU_OCF_QUATERNION_COEFF)
        }
        meanFilter = MeanFilter(FILTER_TIME_CONSTANT)
    }

    override fun isAvailable(): Boolean {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return sensorManager.getDefaultSensor(TYPE_GYROSCOPE) != null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun registerListener() {
        Timber.d("$TAG : Register Listener")
        fSensor?.register(sensorObserver)
        fSensor?.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unRegisterListener() {
        Timber.d("$TAG : Unregister Listener")
        fSensor?.unregister(sensorObserver)
        fSensor?.stop()
    }
}
