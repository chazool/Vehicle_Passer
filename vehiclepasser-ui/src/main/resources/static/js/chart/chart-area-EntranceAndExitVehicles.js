// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function (n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

$("#location-entranceAndExitVehicle").change(function () {

    load_areaChartEntranceAndExitVehicles($(this).val());
});

function load_areaChartEntranceAndExitVehicles(location) {
    var lordingDiv = document.getElementById("areaChart-EntranceAndExitVehicles-lording");
    lordingDiv.style.display = "block";
    $.getJSON("entranceandexit-vehicles/" + location, function (data, textStatus, jqXHR) {

        var labels = [];
        var entranceVehicles = [];
        var exitVehicles = [];

        $.each(data.entrance, function (key, value) {
            labels.push(value.simpleDate);
            entranceVehicles.push(value.vehicleCont);
        });

        $.each(data.exit, function (key, value) {
            exitVehicles.push(value.vehicleCont);
        });


        var entranceData = {
            label: 'Entrance Vehicles',
            data: entranceVehicles,
            borderColor: 'rgba(0, 99, 132, 0.6)',
            borderWidth: 4
        };

        var exitData = {
            label: 'Exit Vehicles',
            data: exitVehicles,
            borderColor: 'rgba(99, 132, 0, 0.6)',
            borderWidth: 4
        };

        var vehicleData = {
            labels: labels,
            datasets: [entranceData, exitData]
        };

        areaChartEntranceAndExitVehicles.data = vehicleData;
        areaChartEntranceAndExitVehicles.update();
        $("#areaChart-EntranceAndExitVehicles-lording").hide();
        lordingDiv.style.display = "none";
    });
}

load_areaChartEntranceAndExitVehicles($("#location-entranceAndExitVehicle").val());


var ctx = document.getElementById("areaChart-EntranceAndExitVehicles");
var areaChartEntranceAndExitVehicles = new Chart(ctx, {
    type: 'line',
    data: {},
    options: {
        maintainAspectRatio: false,
        layout: {
            padding: {
                left: 10,
                right: 25,
                top: 25,
                bottom: 0
            }
        },
        scales: {
            xAxes: [{
                time: {
                    unit: 'date'
                },
                gridLines: {
                    display: false,
                    drawBorder: false
                },
                ticks: {
                    maxTicksLimit: 7
                }
            }],
            yAxes: [{
                ticks: {
                    maxTicksLimit: 5,
                    padding: 10
                },
                gridLines: {
                    color: "rgb(234, 236, 244)",
                    zeroLineColor: "rgb(234, 236, 244)",
                    drawBorder: false,
                    borderDash: [2],
                    zeroLineBorderDash: [2]
                }
            }],
        },
        tooltips: {
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            titleMarginBottom: 10,
            titleFontColor: '#6e707e',
            titleFontSize: 14,
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            intersect: false,
            mode: 'index',
            caretPadding: 10

        }
    }
});



