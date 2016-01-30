<#include "../public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>

    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <div class="row">
            <div class="col-md-4">
                <!-- Default panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        添加新标签
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-12">
                                    <input type="text" class="form-control" id="inputEmail3" placeholder="标签名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">别名</label>

                                <div class="col-sm-12">
                                    <input type="text" class="form-control" id="inputEmail3" placeholder="标签别名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">父节</label>

                                <div class="col-sm-12">
                                    <select class="form-control">
                                        <option value="">无</option>
                                        <option value="">Default select</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-6 col-sm-10">
                                    <button type="submit" class="btn btn-default">添加新分配目录</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

            <div class="col-md-8">
                <!-- Bordered panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        分类目录
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th width="40px"><input type="checkbox"></th>
                                    <th>名称</th>
                                    <th>别名</th>
                                    <th>总数</th>
                                    <th width="50px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th><input type="checkbox"></th>
                                    <th>标题</th>
                                    <td>作者</td>
                                    <td>分类目录</td>
                                    <td>
                                        <a href="#" class="btn btn-danger btn-sm btn-icon icon-left">删除</a>
                                    </td>
                                </tr>
                                <tr>
                                    <th><input type="checkbox"></th>
                                    <th>标题</th>
                                    <td>作者</td>
                                    <td>分类目录</td>
                                    <td>
                                        <a href="#" class="btn btn-danger btn-sm btn-icon icon-left">删除</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    <#include "../public/content-footer.ftl"/>
    </div>

</div>

<#include "../public/footer.ftl"/>