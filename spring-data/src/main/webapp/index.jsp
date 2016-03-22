<%@ page language="java" pageEncoding="UTF-8"%>
<html>
    <meta charset="utf-8">
<head>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <link href="/resources/css/pageUtil.css" type="text/css" rel="stylesheet" />

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


    <script src="/resources/js/pageUtil2.1.1.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            查询结果
        </div>
    </div>
</nav>

<!-- 查询表单 -->
<div class="container-fluid">
<form class="form-inline" onsubmit="return false;">
    <div class="form-group">
        <label for="envId">envId</label>
        <input type="text" class="form-control" id="envId" placeholder="text envId">
    </div>
    <div class="form-group">
        <label for="projectId">projectId</label>
        <input type="text" class="form-control" id="projectId" placeholder="text projectId">
    </div>
    <div class="form-group">
        <label for="level">level</label>
        <input type="text" class="form-control" id="level" placeholder="text level">
    </div>
    <div class="form-group">
        <label for="title">title</label>
        <input type="text" class="form-control" id="title" placeholder="text title">
    </div>
    <div class="form-group">
        <label for="content">content</label>
        <input type="email" class="form-control" id="content" placeholder="text content">
    </div>
    <button id="searchButton" type="button" class="btn btn-default">search</button>
</form>
</div>



<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">查询结果</h3>
    </div>
    <div id="datapanel" class="panel-body">
        无内容
    </div>
</div>


<div id="logtemplate" hidden>
    <p>[#envId#][#projectId#][#date#][#title#]</p>
    <p>#content#</p>
</div>

<div id="page"></div>
</body>

<script>


    $(function(){
        /**
         * 添加分页
         */
        $("#page").pageBar({
            action:getDataAndRefresh,
            async:true,
            pageSize:5,
            pageNum:1
        });

        $("#page").data("pageBar").jumpTo(1);



        $("#searchButton").on("click",function(){
            $("#page").data("pageBar").jumpTo(1);
        })


        function getFormData(){
            var data = {};

            data.content = $("#content").val()? $("#content").val():null;
            data.envId = $("#envId").val()? $("#envId").val():null;
            data.level = $("#level").val()? $("#level").val():null;
            data.projectId = $("#projectId").val()? $("#projectId").val():null;
            data.title = $("#title").val()? $("#title").val():null;

            return data;
        }

        function  showData(data){
            console.info($("#logtemplate").html());

            $("#datapanel").empty();

            for(var x in data.list){
                var template = $("#logtemplate").html();
                template = template.replace(/#envId#/g, data.list[x].envId);
                template = template.replace(/#projectId#/g, data.list[x].projectId);
                template = template.replace(/#title#/g, data.list[x].title);
                template = template.replace(/#date#/g, data.list[x].logDate);
                template = template.replace(/#content#/g, data.list[x].content);
                $("#datapanel").append(template);
            }

        }

        function getDataAndRefresh(pageSize,pageNo){
            var data = getFormData();

            var result ;

            $.ajax({
                url:"/log/querylogsbypage/"+pageNo+"/"+pageSize+"",
                data:JSON.stringify(data),
                processData:false,
                dataType:"json",
                type:"POST",
                contentType:"application/json;charset=utf-8",
                async:true,
                success:function(data){
                    result = data;
                    var pageCount = (result.totalCount-1)/result.pageSize+1;
                    pageCount = Math.floor(pageCount);

                    $("#page").data("pageBar").recreatePage(result.pageSize,result.pageNo,pageCount);

                    showData(result);
                },
                error:function(data){
                    console.info(data);
                }
            });

//            if(result){
//                var r = {};
//                r.pageNum = result.pageNo;
//                r.pageSize = result.pageSize;
//                if(result.totalCount>0){
//                    r.pageCount = (result.totalCount-1)/result.pageSize+1;
//                    r.pageCount = Math.floor(r.pageCount);
//                }else{
//                    r.pageCount = 1;
//                }
//                return r;
//            }
        }
    });
</script>
</html>
