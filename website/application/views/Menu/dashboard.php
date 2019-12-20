<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>

<div class="row wrapper border-bottom white-bg page-heading row mb-3">
    <h1 class="ml-3">Dashboard</h1>
</div>
<div class="container">
    <div class="wrapper wrapper-content">
        <div class="row">
            <!-- <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah data order</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">40 886,200</h1>
                    </div>
                </div>
            </div> -->
            <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah data produk</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins"><?=$totalproduk?></h1>
                    </div>
                </div>
            </div>
            <!-- <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah stok makanan</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">106,120</h1>
                    </div>
                </div>
            </div> -->
            <!-- <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah stok minuman</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">80,600</h1>
                    </div>
                </div>
            </div> -->
        </div>
        <div class="row">
            <div class="col-lg-8">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Grafik Stok</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#" class="dropdown-item">Config option 1</a>
                                </li>
                                <li><a href="#" class="dropdown-item">Config option 2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                    <div class="flot-chart">
                        <!-- <div class="flot-chart-content">  -->
                        <canvas id="canvas" height="210" width="300">
                            <?php
                                foreach($data as $data){
                                $nama_bahan[] = $data->nama_bahan;
                                $jumlah[] = (float) $data->jumlah;
                                }
                             ?>
                            <script type="text/javascript" src="<?php echo base_url().'assets/chartjs/chart.min.js'?>"></script>
	                        <script>

                            var lineChartData = {
                                labels : <?php echo json_encode($nama_bahan);?>,
                                datasets : [
                                    
                                    {
                                        fillColor: "rgba(60,141,188,0.9)",
                                        strokeColor: "rgba(60,141,188,0.8)",
                                        pointColor: "#3b8bba",
                                        pointStrokeColor: "#fff",
                                        pointHighlightFill: "#fff",
                                        pointHighlightStroke: "rgba(152,235,239,1)",
                                        data : <?php echo json_encode($jumlah);?>
                                    }

                                ]
                
                             }

                            var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(lineChartData);    
                            </script>
                        </canvas>
                    </div> 
                    </div> 
                </div>
            </div>
        </div>
        <script type="text/javascript" src="<?php echo base_url().'assets/highcharts/highcharts.js'?>"></script>
        <script src="../../code/modules/exporting.js"></script>
        <script src="../../code/modules/export-data.js"></script>

        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        <script type="text/javascript">
        Highcharts.chart('container', {
            chart: {
                type: 'line'
            },
            title: {
                text: 'Grafik Data Pengunjung'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Total Pengunjung'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: [{
                name: 'Data dalam bulan',
                data: <?php echo json_encode($grafik);?>
            }]
        });
                </script>
    </div>
</div>



<?php $this->load->view('partials/footer.php'); ?>
<?php $this->load->view('partials/js.php'); ?>