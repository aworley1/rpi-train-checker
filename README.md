# Raspberry Pi Train Checker (UK)

An application to check your favourite daily trains against the National Rail API and light up a status LED using the GPIO pins on a Raspberry Pi.

## Requirements

- A Raspberry Pi
- Two LEDs connected to GPIO pins
    - Via a suitable resistor, back to the GND pin
- A developer key for the National Rail OpenLDBWS API (http://realtime.nationalrail.co.uk/OpenLDBWSRegistration/Registration)
- (Optionally) Docker (installation instructions for Raspbian here: https://docs.docker.com/install/linux/docker-ce/debian/#install-using-the-convenience-script)

## Environment Variables

| Name | Example | Description |
| --- | --- | --- |
| TRAINS_REQUESTED_TIMES     | 08:15,08:32,12:45 | Comma separated list of trains you are interested in |
| TRAINS_DEPARTURE_STATION   | HFN | Your departure station (CRS code) |
| TRAINS_DESTINATION_STATION | HHY | Your destination station (CRS code) |
| TRAINS_ACCESS_TOKEN        | abcd-efg-hij | Your OpenLDBWS API key (see above to obtain) |

## Running

Either:

Set the above environment variables, and then:
`./gradlew run`

or

Run using the following Docker command:

```shell script
docker pull aworley1/rpi-train-checker 

docker run \
--env TRAINS_REQUESTED_TIMES="07:15,07:32,07:44,08:12,08:32" \
--env TRAINS_DEPARTURE_STATION="HFN" \
--env TRAINS_DESTINATION_STATION="HHY" \
--env TRAINS_ACCESS_TOKEN="REPLACE_WITH_YOUR_NATIONA_RAIL_API_KEY" \
--cap-add SYS_RAWIO \
--device /dev/gpiomem \
-v /sys/class/gpio:/sys/class/gpio \
-v /sys/devices/platform/soc:/sys/devices/platform/soc \
aworley1/rpi-train-checker
```

(You can download this script from [check-trains.sh](check-trains.sh) - be sure to replace with your own values)

### Running on a schedule

Recommend creating an executable script to pull the latest version and run it:
```shell script
#!/bin/sh

docker pull aworley1/rpi-train-checker > /dev/null 2>&1


docker run \
--env TRAINS_REQUESTED_TIMES="07:15,07:32,07:44,08:12,08:32" \
--env TRAINS_DEPARTURE_STATION=HFN \
--env TRAINS_DESTINATION_STATION=HHY \
--env TRAINS_ACCESS_TOKEN="REPLACE_WITH_YOUR_NATIONA_RAIL_API_KEY" \
--cap-add SYS_RAWIO \
--device /dev/gpiomem \
-v /sys/class/gpio:/sys/class/gpio \
-v /sys/devices/platform/soc:/sys/devices/platform/soc \
aworley1/rpi-train-checker

```
 
then a cron job with `crontab -e` that looks something like this:
```
0,5,10,15,20,23,25,30,35,40,45,50,55 * * * * /home/pi/bin/check-trains.sh
```
(this will run every 5 minutes)

You may also want to create another job to clean up any old docker images that are no longer the current version. NB this is for all Docker images (not just for this applicaiton):
```
0 0 * * * docker system prune -f > /dev/null 2>&1
```

## Output
### No Lights
If you have not requested any trains within the next 90minutes

### A Green Light
If you have requested trains within the next 90minutes, and they have all been returned from the API, none are cancelled, and none are delayed by more than 2 minutes.

### A Red Light
If you have requested trains within the next 90minutes, and any are missing, cancelled, or delayed by more than 2 minutes.