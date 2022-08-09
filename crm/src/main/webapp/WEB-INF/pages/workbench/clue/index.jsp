<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">

	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">

	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>

	<script type="text/javascript">

		$(function(){
			//给"创建"按钮添加单击事件
			$("#createClueBtn").click(function () {
				//初始化工作
				$("#createClueForm")[0].reset();
				//所有者选中当前登录用户
				$("#create-owner").val('${sessionScope.sessionUser.id}')
				//弹出模态窗口
				$("#createClueModal").modal("show");
			});

			//当容器加载完成之后，对容器调用工具函数
			//$("input[name='mydate']").datetimepicker({
			$(".mydate").datetimepicker({
				language:'zh-CN', //语言
				format:'yyyy-mm-dd',//日期的格式
				pickerPosition:'top-right',//将组件显示在元素上方
				minView:'month', //可以选择的最小视图
				initialDate:new Date(),//初始化显示的日期
				autoclose:true,//设置选择完日期或者时间之后，日否自动关闭日历
				todayBtn:true,//设置是否显示"今天"按钮,默认是false
				clearBtn:true//设置是否显示"清空"按钮，默认是false
			});

			//给"保存"按钮添加单击事件
			$("#saveCreateClueBtn").click(function () {
				saveCreateClue()
			});
			//给填写地址的的文本框添加键盘单击事件
			$("#create-address").keydown(function (event) {
				if (event.keyCode==13){
					saveCreateClue()
				}
			})

			//页面加载完毕后执行查询线索
			selectClueByConditionForPage(1,5)

			//给查询按钮绑定单击事件
			$("#selectClueBtn").click(function () {
				//刷新线索列，显示第一页数据，保持每页显示条数不变(保留)
				selectClueByConditionForPage(1,$("#timepicker").bs_pagination('getOption', 'rowsPerPage'))
			})
			//给查询的文本框绑定键盘事件
			$(".selectClue").keydown(function (event) {
				if (event.keyCode==13){
					selectClueByConditionForPage(1,$("#timepicker").bs_pagination('getOption', 'rowsPerPage'))
				}
			})
			//给查询线索来源的下拉列表添加事件
			$("#source").change(function (){
				selectClueByConditionForPage(1,$("#timepicker").bs_pagination('getOption', 'rowsPerPage'))
			})
			//给查询线索状态的下拉列表添加事件
			$("#state").change(function () {
				selectClueByConditionForPage(1,$("#timepicker").bs_pagination('getOption', 'rowsPerPage'))
			})
		});
		//创建线索
		saveCreateClue=function () {
			//收集参数
			var fullname       =$.trim($("#create-fullname").val());
			var appellation    =$("#create-appellation").val();
			var owner          =$("#create-owner").val();
			var company        =$.trim($("#create-company").val());
			var job            =$.trim($("#create-job").val());
			var email          =$.trim($("#create-email").val());
			var phone          =$.trim($("#create-phone").val());
			var website        =$.trim($("#create-website").val());
			var mphone         =$.trim($("#create-mphone").val());
			var state          =$("#create-state").val();
			var source         =$("#create-source").val();
			var description    =$.trim($("#create-description").val());
			var contactSummary =$.trim($("#create-contactSummary").val());
			var nextContactTime=$.trim($("#create-nextContactTime").val());
			var address        =$.trim($("#create-address").val());
			//表单验证(作业)

			//带*非空
			if (company==null || company==""){
				alert("公司不能为空")
				return;
			}else if (fullname==null || fullname==""){
				alert("姓名不能为空")
				return;
			}
			//正则表达式验证
			//Email地址
			var emailNum = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
			if (!emailNum.test(email)){
				alert("请输入合法的邮箱地址")
				return;
			}
			//固定电话号码
			/*var phoneNum = /^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$/
			if (!phoneNum.test(phone)){
				alert("请输入合法的公司座机")
				return;
			}*/
			//手机号
			var mobilePhoneNum =/^1(3|4|5|6|7|8|9)\d{9}$/
			if (!mobilePhoneNum.test(mphone)){
				alert("请输入合法的手机号")
				return;
			}
			//域名
			var domainName = /^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$/
			if (!domainName.test(website)){
				alert("请输入合法的公司网站")
				return;
			}
			//发送请求
			$.ajax({
				url:'workbench/clue/saveCreateClue.do',
				data:{
					fullname       :fullname       ,
					appellation    :appellation    ,
					owner          :owner          ,
					company        :company        ,
					job            :job            ,
					email          :email          ,
					phone          :phone          ,
					website        :website        ,
					mphone         :mphone         ,
					state          :state          ,
					source         :source         ,
					description    :description    ,
					contactSummary :contactSummary ,
					nextContactTime:nextContactTime,
					address        :address
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if(data.code=="1"){
						//关闭模态窗口
						$("#createClueModal").modal("hide");
						//刷新线索列表，显示第一页数据，保持每页显示条数不变(作业)
						selectClueByConditionForPage(1,$("#timepicker").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						//提示信息
						alert(data.message);
						//模态窗口不关闭
						$("#createClueModal").modal("show");
					}
				}
			});
		}
		//条件分页查询
		function selectClueByConditionForPage(pageNo,pageSize) { 
			//收集参数
			var fullname = $("#fullname").val()
			var owner = $("#owner").val()
			var company = $("#company").val()
			var phone = $("#phone").val()
			var mphone = $("#mphone").val()
			var state = $("#state").val()
			var source = $("#source").val()
			//var pageNo=1;
			//var pageSize=10;
			//发送请求
			$.ajax({
				url:'workbench/clue/selectClueByConditionForPage.do',
				data:{
					fullname:fullname,
					owner :owner,
					company:company,
					phone :phone,
					mphone :mphone,
					state :state,
					source :source,
					pageNo:pageNo,
					pageSize:pageSize
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					//显示总条数
					//$("#totalRowsB").html("共<b>"+data.totalRows+"</b>条记录");
					//显示市场活动的列表
					//遍历activityList，拼接所有行数据
					var htmlStr="";
					$.each(data.clueList,function (index,obj) {
						htmlStr+="<tr>"
						htmlStr+="<td id='+obj.id+'><input type='checkbox' /></td>"
						htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/clue/detailClue.do?id="+obj.id+"';\">"+obj.fullname+obj.appellation+"</a></td>"
						htmlStr+="<td>"+obj.company+"</td>"
						htmlStr+="<td>"+obj.phone+"</td>"
						htmlStr+="<td>"+obj.mphone+"</td>"
						htmlStr+="<td>"+obj.source+"</td>"
						htmlStr+="<td>"+obj.owner+"</td>"
						htmlStr+="<td>"+obj.state+"</td>"
						htmlStr+="</tr>"
					});
					$("#tBody").html(htmlStr);

					//取消"全选"按钮
					$("#checkAll").prop("checked",false);

					//计算总页数
					let totalPages = 1;
					if(data.totalRows%pageSize==0){
						totalPages=data.totalRows/pageSize;
					}else{
						totalPages=parseInt(data.totalRows/pageSize)+1;
					}

					//对容器调用bs_pagination工具函数，显示翻页信息
					$("#timepicker").bs_pagination({
						currentPage:pageNo,//当前页号,相当于pageNo

						rowsPerPage:pageSize,//每页显示条数,相当于pageSize
						totalRows:data.totalRows,//总条数
						totalPages: totalPages,  //总页数,必填参数.

						visiblePageLinks:5,//最多可以显示的卡片数

						showGoToPage:true,//是否显示"跳转到"部分,默认true--显示
						showRowsPerPage:true,//是否显示"每页显示条数"部分。默认true--显示
						showRowsInfo:true,//是否显示记录的信息，默认true--显示

						//用户每次切换页号，都自动触发本函数;
						//每次返回切换页号之后的pageNo和pageSize
						onChangePage: function(event,pageObj) { // returns page_num and rows_per_page after a link has clicked
							//js代码
							//alert(pageObj.currentPage);
							//alert(pageObj.rowsPerPage);
							selectClueByConditionForPage(pageObj.currentPage,pageObj.rowsPerPage);
						}
					});
				}
			});
		}

	</script>
</head>
<body>

<!-- 创建线索的模态窗口 -->
<div class="modal fade" id="createClueModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 90%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">创建线索</h4>
			</div>
			<div class="modal-body">
				<form id="createClueForm" class="form-horizontal" role="form">

					<div class="form-group">
						<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="create-owner">
								<c:forEach items="${userList}" var="u">
									<option value="${u.id}">${u.name}</option>
								</c:forEach>
							</select>
						</div>
						<label for="create-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-company">
						</div>
					</div>

					<div class="form-group">
						<label for="create-appellation" class="col-sm-2 control-label">称呼</label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="create-appellation">
								<option></option>
								<c:forEach items="${appellationList}" var="app">
									<option value="${app.id}">${app.value}</option>
								</c:forEach>
							</select>
						</div>
						<label for="create-fullname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-fullname">
						</div>
					</div>

					<div class="form-group">
						<label for="create-job" class="col-sm-2 control-label">职位</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-job">
						</div>
						<label for="create-email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-email">
						</div>
					</div>

					<div class="form-group">
						<label for="create-phone" class="col-sm-2 control-label">公司座机</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-phone">
						</div>
						<label for="create-website" class="col-sm-2 control-label">公司网站</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-website">
						</div>
					</div>

					<div class="form-group">
						<label for="create-mphone" class="col-sm-2 control-label">手机</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="create-mphone">
						</div>
						<label for="create-state" class="col-sm-2 control-label">线索状态</label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="create-state">
								<option></option>
								<c:forEach items="${clueStateList}" var="cs">
									<option value="${cs.id}">${cs.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="create-source" class="col-sm-2 control-label">线索来源</label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="create-source">
								<option></option>
								<c:forEach items="${sourceList}" var="sl">
									<option value="${sl.id}">${sl.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>


					<div class="form-group">
						<label for="create-description" class="col-sm-2 control-label">线索描述</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea class="form-control" rows="3" id="create-description"></textarea>
						</div>
					</div>

					<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

					<div style="position: relative;top: 15px;">
						<div class="form-group">
							<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-contactSummary"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-nextContactTime" readonly>
							</div>
						</div>
					</div>

					<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

					<div style="position: relative;top: 20px;">
						<div class="form-group">
							<label for="create-address" class="col-sm-2 control-label">详细地址</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="1" id="create-address"></textarea>
							</div>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="saveCreateClueBtn">保存</button>
			</div>
		</div>
	</div>
</div>



<!-- 修改线索的模态窗口 -->
<div class="modal fade" id="editClueModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 90%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">修改线索</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">

					<div class="form-group">
						<label for="edit-clueOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="edit-clueOwner">
								<c:forEach items="${userList}" var="u">
									<option value="${u.id}">${u.name}</option>
								</c:forEach>
							</select>
						</div>
						<label for="edit-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-company" value="动力节点">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-call" class="col-sm-2 control-label">称呼</label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="edit-call">
								<option></option>
								<c:forEach items="${appellationList}" var="app">
									<option value="${app.id}">${app.value}</option>
								</c:forEach>
							</select>
						</div>
						<label for="edit-surname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-surname" value="李四">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-job" class="col-sm-2 control-label">职位</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-job" value="CTO">
						</div>
						<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-email" value="lisi@bjpowernode.com">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-phone" value="010-84846003">
						</div>
						<label for="edit-website" class="col-sm-2 control-label">公司网站</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-website" value="http://www.bjpowernode.com">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-mphone" value="12345678901">
						</div>
						<label for="edit-status" class="col-sm-2 control-label">线索状态</label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="edit-status">
								<option></option>
								<c:forEach items="${clueStateList}" var="cs">
									<option value="${cs.id}">${cs.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="edit-source" class="col-sm-2 control-label">线索来源</label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="edit-source">
								<option></option>
								<c:forEach items="${sourceList}" var="sl">
									<option value="${sl.id}">${sl.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="edit-describe" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea class="form-control" rows="3" id="edit-describe">这是一条线索的描述信息</textarea>
						</div>
					</div>

					<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

					<div style="position: relative;top: 15px;">
						<div class="form-group">
							<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-contactSummary">这个线索即将被转换</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control  mydate" id="edit-nextContactTime" readonly/>
							</div>
						</div>
					</div>

					<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

					<div style="position: relative;top: 20px;">
						<div class="form-group">
							<label for="edit-address" class="col-sm-2 control-label">详细地址</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="1" id="edit-address">北京大兴区大族企业湾</textarea>
							</div>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
			</div>
		</div>
	</div>
</div>




<div>
	<div style="position: relative; left: 10px; top: -10px;">
		<div class="page-header">
			<h3>线索列表</h3>
		</div>
	</div>
</div>

<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

	<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

		<div class="btn-toolbar" role="toolbar" style="height: 80px;">
			<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">名称</div>
						<input id="fullname" class="form-control selectClue" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">公司</div>
						<input id="company" class="form-control selectClue" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">公司座机</div>
						<input id="phone" class="form-control selectClue" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">线索来源</div>
						<select id="source" class="form-control">
							<option></option>
							<c:forEach items="${sourceList}" var="sl">
								<option value="${sl.id}">${sl.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<br>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">所有者</div>
						<input id="owner" class="form-control selectClue" type="text">
					</div>
				</div>



				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">手机</div>
						<input id="mphone" class="form-control selectClue" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">线索状态</div>
						<select id="state" class="form-control">
							<option></option>
							<c:forEach items="${clueStateList}" var="cs">
								<option value="${cs.id}">${cs.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<button type="button" id="selectClueBtn" class="btn btn-default">查询</button>

			</form>
		</div>
		<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 40px;">
			<div class="btn-group" style="position: relative; top: 18%;">
				<button type="button" class="btn btn-primary" id="createClueBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#editClueModal"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
			</div>


		</div>
		<div style="position: relative;top: 50px;">
			<table class="table table-hover">
				<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" /></td>
					<td>名称</td>
					<td>公司</td>
					<td>公司座机</td>
					<td>手机</td>
					<td>线索来源</td>
					<td>所有者</td>
					<td>线索状态</td>
				</tr>
				</thead>
				<tbody id="tBody">

				</tbody>
			</table>
			<div id="timepicker"></div>
		</div>



	</div>

</div>
</body>
</html>