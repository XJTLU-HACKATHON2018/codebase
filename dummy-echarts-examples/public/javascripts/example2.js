


// configure for module loader
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});


// Shared variable
var myChart;

// As the entrance
require(
    [
        'echarts',
        'echarts/chart/k' // Required by the candlestick plot
    ],
    function (ec) {
        // Just assign it globally....
        window.ec = ec;
        redraw();
    }
);

// Function to draw the plot
function redraw() {
    console.log("Drawing");

    // The plot has not been initialized yet.
    if (myChart === undefined)
        myChart = ec.init(document.getElementById('MyECharts'));


    // Fetch the data
    fetch("/dummydata2")
        .then(function(res) {
            return res.json();
        })
        .then(function(res) {
            // Set the option
            myChart.setOption(EChartOption(res));
        })
        .catch(err => console.log(err));
};


// Generate EChart option from fetched data
function EChartOption(res) {
    
    const option_proto = {
        title : {
            text: 'Dummy K line Plot2'
        },
        tooltip : {
            trigger: 'axis',
            formatter: function (params) {
                var res = params[0].seriesName + ' ' + params[0].name;
                res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                return res;
            }
        },
        legend: {
            data:['上证指数']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataZoom : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        dataZoom : {
            show : true,
            realtime: true,
            start : 50,
            end : 100
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : true,
                axisTick: {onGap:false},
                splitLine: {show:false},
                //// Will be overwritten ///////////////////////
                data : [
                    "2013/1/24", "2013/1/25"
                ]
            }
        ],
        yAxis : [
            {
                type : 'value',
                scale:true,
                boundaryGap: [0.01, 0.01]
            }
        ],
        series : [
            {
                name:'上证指数',
                type:'k',
                //// Will be overwritten ///////////////////////
                data:[ // 开盘，收盘，最低，最高
                    [2320.26,2302.6,2287.3,2362.94],
                    [2300,2291.3,2288.26,2308.38]
                ]
            }
        ]
    };

    option_proto.xAxis[0].data = res.xaxis;
    option_proto.series[0].data = res.data;

    return option_proto;
};













