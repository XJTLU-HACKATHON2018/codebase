<template>
  <chart :options="chartOptions" ref="chart" auto-resize></chart>
</template>

<script>
 // The rawdata is stored in a json file
 import * as rawData0 from "./data/rawData.json"
 var rawData = rawData0.reverse();

function calculateMA(dayCount, data) {
  var result = [];
  for (var i = 0, len = data.length; i < len; i++) {
    if (i < dayCount) {
      result.push("-");
      continue;
    }
    var sum = 0;
    for (var j = 0; j < dayCount; j++) {
      sum += data[i - j][1];
    }
    result.push(sum / dayCount);
  }
  return result;
}
var dates = rawData.map(function(item) {
  return item[0];
});

var data = rawData.map(function(item) {
  return [+item[1], +item[2], +item[5], +item[6]];
});

export default {
  name: "CandlestickETH",
  computed: {},
  data() {
    return {
      chartOptions: {
        axisLabel: {
          color: "#fff"
        },
        /** grid配置 */
        grid: {
          borderWidth: 0,
          top: 12,
          bottom: 10,
          left: 0,
          right: 10,
          containLabel: true
        },
        dataZoom: [
          {
            textStyle: {
              color: "#8392A5"
            },
            handleIcon:
              "M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z",
            handleSize: "80%",
            dataBackground: {
              areaStyle: {
                color: "#8392A5"
              },
              lineStyle: {
                opacity: 0.8,
                color: "#8392A5"
              }
            },
            handleStyle: {
              color: "#fff",
              shadowBlur: 3,
              shadowColor: "rgba(0, 0, 0, 0.6)",
              shadowOffsetX: 2,
              shadowOffsetY: 2
            }
          },
          {
            type: "inside"
          }
        ],
        legend: {
          data: ['ETH', 'MA5', 'MA10', 'MA20', 'MA30'],
          inactiveColor: '#777',
          textStyle: {
            color: '#fff'
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            animation: false,
            type: 'cross',
            lineStyle: {
                color: '#376df4',
                width: 2,
                opacity: 1
            }
        }
    },
        xAxis: {
          snap: true,
          axisLine: { lineStyle: { color: "#8392A5" } },
          data: dates
        },
        yAxis: {
          axisLine: { lineStyle: { color: "#8392A5" } },
          splitLine: {
            lineStyle: {
              color: "#384046"
            }
          }
        },
        animation: false,
        series: [
          {
            type: "candlestick",
            name: "K",
            data: data,
            itemStyle: {
              normal: {
                color: "#FD1050",
                color0: "#0CF49B",
                borderColor: "#FD1050",
                borderColor0: "#0CF49B"
              }
            }
          },
          {
            name: "MA5",
            type: "line",
            data: calculateMA(5, data),
            smooth: true,
            showSymbol: false,
            lineStyle: {
              normal: {
                width: 1
              }
            }
          },
          {
            name: "MA10",
            type: "line",
            data: calculateMA(10, data),
            smooth: true,
            showSymbol: false,
            lineStyle: {
              normal: {
                width: 1
              }
            }
          },
          {
            name: "MA20",
            type: "line",
            data: calculateMA(20, data),
            smooth: true,
            showSymbol: false,
            lineStyle: {
              normal: {
                width: 1
              }
            }
          },
          {
            name: "MA30",
            type: "line",
            data: calculateMA(30, data),
            smooth: true,
            showSymbol: false,
            lineStyle: {
              normal: {
                width: 1
              }
            }
          }
        ]
      }
    };
  },
  created() {
    // 监听窗口大小变化
  },
  methods: {},
  watch: {}
};
</script>

<style lang="css" scoped>

</style>
