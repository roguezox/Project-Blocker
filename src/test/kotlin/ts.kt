// Import the java.time classes
import org.apache.commons.net.ntp.NTPUDPClient
import java.net.InetAddress
import java.sql.Time
import java.time.Duration
import java.time.LocalTime
import java.time.temporal.Temporal
import java.time.temporal.TemporalAmount
import java.time.temporal.TemporalUnit
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit



fun main() {

    val ntpClient = NTPUDPClient()
    ntpClient.setDefaultTimeout(Duration.ofSeconds(15))
    ntpClient.open()
    val info = ntpClient.getTime(InetAddress.getByName("time.google.com"))
    ntpClient.close()

    val ntpTime = info.message.transmitTimeStamp.time
    val time = Time(ntpTime)
    print(time.toLocalTime())



}

