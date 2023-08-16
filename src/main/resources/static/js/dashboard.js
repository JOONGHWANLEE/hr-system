$(function () {

  // =====================================
  // Profit as Pie Chart
  // =====================================
  var pieChart = {
    series: [15, 0, 1, 2, 1, 3],
    labels: ["출근인원", "휴가인원", "연/월차 인원", "출장 인원", "퇴근 인원", "지각 인원"],
    
    chart: {
      type: "donut",
      height: 345,
      toolbar: { show: true },
      foreColor: "#adb0bb",
      fontFamily: 'inherit',
      sparkline: { enabled: false },
    },

    colors: ["#5D87FF", "#49BEFF", "#433d98", "#97499e", "#f2bb07", "#c5cdd7"],

    plotOptions: {
      pie: {
        donut: {
          size: '50%',
          labels: {
            show: true,
            total: {
              show: true,
              label: '출근율',
              fontSize: '22px',
              fontFamily: 'Helvetica, Arial, sans-serif',
              fontWeight: 600,
              color: '#373d3f',
              offsetY: -10,
              formatter: function (w) {
                return w.globals.seriesTotals.reduce((a, b) => a + b, 0)
              }
            }
          }
        }
      }
    },

    dataLabels: {
      enabled: false,
    },

    legend: {
      show: true,
    },

    tooltip: { theme: "light" },
  };

  new ApexCharts(document.querySelector("#chart"), pieChart).render();
})
