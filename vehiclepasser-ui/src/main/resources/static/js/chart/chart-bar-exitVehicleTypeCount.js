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

$("#location-exitVehicleType").change(function () {
    load_BarChartExitVehicleTypes($(this).val());
});

function load_BarChartExitVehicleTypes(location) {

    var lordingDiv = document.getElementById("barChart-ExitVehicleTypes-lording");
    lordingDiv.style.display = "block";


    $.getJSON("exit-vehicletypes/" + location, function (data, textStatus, jqXHR) {

        var labels = [];
        var vehicleType1_Data = [];
        var vehicleType2_Data = [];
        var vehicleType3_Data = [];

        var vehicleType1_label = [];
        var vehicleType2_label = [];
        var vehicleType3_label = [];


        var count = 1;
        $.each(data, function (key, value) {
            if (count === 1) {
                vehicleType1_label = key;
                $.each(value, function (key, value) {
                    labels.push(key);
                    vehicleType1_Data.push(value);
                });

            } else if (count === 2) {
                vehicleType2_label = key;
                $.each(value, function (key, value) {
                    vehicleType2_Data.push(value);
                });
            } else if (count == 3) {
                vehicleType3_label = key;
                $.each(value, function (key, value) {
                    vehicleType3_Data.push(value);
                });
            }
            count++;
        });

        var vehicleType1 = {
            label: vehicleType1_label,
            data: vehicleType1_Data,
            backgroundColor: 'rgba(78, 115, 223, 1)',
            borderWidth: 0
        };

        var vehicleType2 = {
            label: vehicleType2_label,
            data: vehicleType2_Data,
            backgroundColor: 'rgba(28, 200, 138, 1)',
            borderWidth: 0
        };

        var vehicleType3 = {
            label: vehicleType3_label,
            data: vehicleType3_Data,
            backgroundColor: 'rgba(54, 185, 204, 1)',
            borderWidth: 0
        };

        var vehicleTypeData = {
            labels: labels,
            datasets: [vehicleType1, vehicleType2, vehicleType3]
        };

        barChartExitVehicleTypes.data = vehicleTypeData;
        barChartExitVehicleTypes.update();
        lordingDiv.style.display = "none";
    });
}

load_BarChartExitVehicleTypes($("#location-exitVehicleType").val());


// Bar Chart Example
var ctx = document.getElementById("barChart-ExitVehicleTypes");
var barChartExitVehicleTypes = new Chart(ctx, {
    type: 'bar',
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
                    unit: 'month'
                },
                gridLines: {
                    display: false,
                    drawBorder: false
                },
                ticks: {
                    maxTicksLimit: 7
                },
                maxBarThickness: 25,
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
            titleMarginBottom: 10,
            titleFontColor: '#6e707e',
            titleFontSize: 14,
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            caretPadding: 10
        },
    }
});
