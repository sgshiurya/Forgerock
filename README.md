curl --location --request POST 'http://localhost:8080/forgerock/v1/json/processor/process' \
--header 'Content-Type: application/json' \
--data-raw '{
"config": {
"id": 1,
"name": "DeviceFeatures",
"transforms": [{
"name": "device_os",
"useInML": true,
"enabled": true,
"jsltExpression": ".device.osType"
},
{

				"name": "device_description",
				"useInML": true,
				"enabled": true,
				"jsltExpression": ".device.osType + \" \" + .device.model"
			}
		]
	},
	"payload": {
		"device": {
			"osType": "Linux",
			"model": "Laptop"
		}
	}
}'