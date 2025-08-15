document.getElementById("searchBtn").addEventListener("click", function () {

    var city = document.getElementById("cityInput").value;

    fetch(`/weather?city=${city}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(msg => { throw new Error(msg) });
            }
            return response.json();
        })
        .then(data => {
            console.log("Weather data: ", data);

            const temp = data.main.temp.toFixed(1);
            const feelsLike = data.main.feels_like.toFixed(1);
            const humidity = data.main.humidity;
            const wind = data.wind.speed;
            const description = data.weather[0].description;
            const icon = data.weather[0].icon;

            document.getElementById("data").innerHTML = `
                        <div class="weather-box">
                            <h2>${data.name}, ${data.sys.country}</h2>
                            <img class="weather-icon" src="https://openweathermap.org/img/wn/${icon}@2x.png" alt="${description}">
                            <p><strong>${description.charAt(0).toUpperCase() + description.slice(1)}</strong></p>
                            <p>🌡 Temperature: ${temp}°C (Feels like ${feelsLike}°C)</p>
                            <p>💧 Humidity: ${humidity}%</p>
                            <p>💨 Wind: ${wind} m/s</p>
                        </div>
                    `;


        })
        .catch(error => {
            document.getElementById("data").innerHTML = error.message;
        });

});

