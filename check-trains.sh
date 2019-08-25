#!/bin/sh

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