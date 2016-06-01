<%--
  Created by IntelliJ IDEA.
  User: DaH4uk
  Date: 15.05.2016
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <!-- Page Content -->

        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">CBMS</h1>
                        <ol class="breadcrumb">
                            <li><a href="/">Главная</a>
                            </li>
                            <li class="active">CBMS</li>
                        </ol>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <h3>Доступные параметры:</h3>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped">
                                            ${tabletheadTable}
                                        <thead>
                                        <tr>
                                                ${theadTable}

                                        </tr>
                                        </thead>
                                        <tbody>
                                            ${tableBody}
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

        </div>
        <!-- /.container -->
    </jsp:body>
</page:template>

