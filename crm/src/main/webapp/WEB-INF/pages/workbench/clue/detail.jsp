<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">

	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

	<script type="text/javascript">

		//默认情况下取消和保存按钮是隐藏的
		var cancelAndSaveBtnDefault = true;

		$(function(){
			$("#remark").focus(function(){
				if(cancelAndSaveBtnDefault){
					//设置remarkDiv的高度为130px
					$("#remarkDiv").css("height","130px");
					//显示
					$("#cancelAndSaveBtn").show("2000");
					cancelAndSaveBtnDefault = false;
				}
			});

			$("#cancelBtn").click(function(){
				//显示
				$("#cancelAndSaveBtn").hide();
				//设置remarkDiv的高度为130px
				$("#remarkDiv").css("height","90px");
				cancelAndSaveBtnDefault = true;
			});

			/*$(".remarkDiv").mouseover(function(){
				$(this).children("div").children("div").show();
			});*/
			$("#remarkDivList").on("mouseover",".remarkDiv",function () {
				$(this).children("div").children("div").show();
			});

			/*$(".remarkDiv").mouseout(function(){
				$(this).children("div").children("div").hide();
			});*/
			$("#remarkDivList").on("mouseout",".remarkDiv",function () {
				$(this).children("div").children("div").hide();
			});

			/*$(".myHref").mouseover(function(){
				$(this).children("span").css("color","red");
			});*/
			$("#remarkDivList").on("mouseover",".myHref",function () {
				$(this).children("span").css("color","red");
			});

			/*$(".myHref").mouseout(function(){
				$(this).children("span").css("color","#E6E6E6");
			});*/
			$("#remarkDivList").on("mouseout",".myHref",function () {
				$(this).children("span").css("color","#E6E6E6");
			});

			//给"关联市场活动"按钮添加单击事件
			$("#bundActivityBtn").click(function () {
				//初始化工作
				//清空搜索框
				$("#searchActivityTxt").val("");
				//清空搜索的市场活动列表
				$("#tBody").html("");

				//弹出"线索关联市场活动"的模态窗口
				$("#bundModal").modal("show");
			});

			//给"全选"按钮添加单击事件
			$("#checkAll").click(function () {
				$("#tBody input[type='checkbox']").prop("checked",this.checked);
			});
			//给所有单个复选框 添加单击事件
			$("#tBody").on("click","input[type='checkbox']",function () {
				//如果列表中的所有checkbox都选中，则"全选"按钮也选中
				if($("#tBody input[type='checkbox']").size()==$("#tBody input[type='checkbox']:checked").size()){
					$("#checkAll").prop("checked",true);
				}else{//如果列表中的所有checkbox至少有一个没选中，则"全选"按钮也取消
					$("#checkAll").prop("checked",false);
				}
			});

			//给市场活动搜索框添加键盘弹起事件
			$("#searchActivityTxt").keyup(function () {
				//获取搜索文本框的值
				//当文本框中有值时,查询
				if ($("#searchActivityTxt").val().length>0){
					//收集参数
					var activityName=this.value;
					var clueId='${clue.id}';
					//发送请求
					$.ajax({
						url:'workbench/clue/queryActivityForDetailByNameClueId.do',
						data:{
							activityName:activityName,
							clueId:clueId
						},
						type:'post',
						dataType:'json',
						success:function (data) {
							//遍历data，显示搜索到的市场活动列表
							var htmlStr="";
							$.each(data,function (index,obj) {
								htmlStr+="<tr>";
								htmlStr+="<td><input type=\"checkbox\" value=\""+obj.id+"\"/></td>";
								htmlStr+="<td>"+obj.name+"</td>";
								htmlStr+="<td>"+obj.startDate+"</td>";
								htmlStr+="<td>"+obj.endDate+"</td>";
								htmlStr+="<td>"+obj.owner+"</td>";
								htmlStr+="</tr>";
							});
							$("#tBody").html(htmlStr);
						}
					});
				}

			});

			//给"关联"按钮添加单击事件
			$("#saveBundActivityBtn").click(function () {
				//收集参数
				//获取列表中所有被选中的checkbox
				var chckedIds=$("#tBody input[type='checkbox']:checked");
				//表单验证
				if(chckedIds.size()==0){
					alert("请选择要关联的市场活动");
					return;
				}
				var ids="";
				$.each(chckedIds,function () {//activityId=xxxx&activityId=xxxx&....&activityId=xxxx&
					ids+="activityId="+this.value+"&";
				});
				ids+="clueId=${clue.id}";//activityId=xxxx&activityId=xxxx&....&activityId=xxxx&clueId=xxxxx

				//发送请求
				$.ajax({
					url:'workbench/clue/saveClueActivityRelation.do',
					data:ids,
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code=="1"){
							//关闭模态窗口
							$("#bundModal").modal("hide");
							//刷新已经关联过的市场活动列表
							var htmlStr="";
							$.each(data.returnData,function (index,obj) {
								htmlStr+="<tr id=\"tr_"+obj.id+"\">";
								htmlStr+="<td>"+obj.name+"</td>";
								htmlStr+="<td>"+obj.startDate+"</td>";
								htmlStr+="<td>"+obj.endDate+"</td>";
								htmlStr+="<td>"+obj.owner+"</td>";
								htmlStr+="<td><a href=\"javascript:void(0);\" activityId=\""+obj.id+"\"  style=\"text-decoration: none;\"><span class=\"glyphicon glyphicon-remove\"></span>解除关联</a></td>";
								htmlStr+="</tr>";
							});
							$("#relationedTBody").append(htmlStr);
						}else{
							//提示信息
							alert(data.message);
							//模态窗口不关闭
							$("#bundModal").modal("show");
						}
					}
				});
			});

			//给添加备注按钮添加单击事件
			$("#createClueRemarkBtn").click(function () {
				createClueRemark()
			})
			//给添加备注的文本框添加键盘事件
			$("#remark").keydown(function (event) {
				if (event.keyCode==13){
					createClueRemark()
				}
			})

			//给“更新”按钮添加单击事件
			$("#updateRemarkBtn").click(function () {
				updateClueRemark()
			});
			//给更新文本框添加键盘事件
			$("#edit-noteContent").keydown(function (event) {
				if (event.keyCode==13){
					updateClueRemark()
				}
			})


			//给所有线索备注后边的"修改"图标添加单击事件
			$("#remarkDivList").on("click","a[name='editA']",function () {
				//获取备注的id和noteContent
				var id=$(this).attr("remarkId");
				var noteCotent=$("#div_"+id+" h5").text();
				//把备注的id和noteContent写到修改备注的模态窗口中
				$("#edit-id").val(id);
				$("#edit-noteContent").val(noteCotent);
				//弹出修改市场活动备注的模态窗口
				$("#editRemarkModal").modal("show");
			});
			//给所有的"删除"图标添加单击事件
			$("#remarkDivList").on("click","a[name='deleteA']",function () {
				//收集参数
				var id=$(this).attr("remarkId");
				//发送请求
				$.ajax({
					url:'workbench/activity/deleteClueRemarkByClueId.do',
					data:{
						id:id
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code=="1"){
							//刷新备注列表
							$("#div_"+id).remove();
						}else{
							//提示信息
							alert(data.message);
						}
					}
				});
			});



			//给所有的"解除关联"按钮添加单击事件
			$("#relationedTBody").on("click","a",function () {
				//收集参数
				var activityId=$(this).attr("activityId");
				var clueId="${clue.id}";

				if(window.confirm("确定解除关联吗？")){
					//发送请求
					$.ajax({
						url:'workbench/clue/deleteClueActivityRelation.do',
						data:{
							activityId:activityId,
							clueId:clueId
						},
						type:'post',
						dataType:'json',
						success:function (data) {
							if(data.code=="1"){
								//刷新已经关联的市场活动列表
								$("#tr_"+activityId).remove();
							}else{
								//提示信息
								alert(data.message);
							}
						}
					});
				}
			});

			//给"转换"按钮添加单击事件
			$("#convertClueBtn").click(function () {
				//收集参数
				var id='${clue.id}';
				//发送同步请求
				window.location.href="workbench/clue/toConvert.do?id="+id;
			});
		});
		//创建线索备注
		createClueRemark=function () {
			//收集参数
			var clueId="${clue.id}";
			var noteContent = $.trim($("#remark").val())
			//表单验证
			if(noteContent==""){
				alert("备注内容不能为空");
				return;
			}
			$.ajax({
				url:'workbench/activity/createClueRemark.do',
				data:{
					clueId:clueId,
					noteContent:noteContent
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if(data.code=="1"){
						//清空输入框
						$("#remark").val("")
						var htmlStr="";
						//刷新备注列表
						htmlStr+="<div class=\"remarkDiv\" id=\"div_"+data.returnData.id+"\" style=\"height: 60px;\">"
						htmlStr+="<img title=\"${remark.createBy}\" src=\"image/user-thumbnail.png\" style=\"width: 30px; height:30px;\">"
						htmlStr+="<div style=\"position: relative; top: -40px; left: 40px;\" >"
						htmlStr+="<h5>"+data.returnData.noteContent+"</h5>"
						htmlStr+="<font color=\"gray\">线索</font> <font color=\"gray\">-</font> <b>${clue.fullname}${clue.appellation}-${clue.company}</b> <small style=\"color: gray;\"> "+data.returnData.createTime+"由${sessionScope.sessionUser.name} 创建</small>"
						htmlStr+="<div style=\"position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;\">"
						htmlStr+="<a class=\"myHref\" name=\"editA\" remarkId=\""+data.returnData.id+"\" href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-edit\" style=\"font-size: 20px; color: #E6E6E6;\"></span></a>"
						htmlStr+="&nbsp;&nbsp;&nbsp;&nbsp;"
						htmlStr+="<a class=\"myHref\" name=\"deleteA\" remarkId=\""+data.returnData.id+"\" href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-remove\" style=\"font-size: 20px; color: #E6E6E6;\"></span></a>"
						htmlStr+="</div>"
						htmlStr+="</div>"
						htmlStr+="</div>"
						$("#remarkDiv").before(htmlStr);
					}else {
						alert(data.message)
					}
				}
			})
		}
		//更新备注
		updateClueRemark=function () {
			//收集参数
			var id=$("#edit-id").val();
			var noteContent=$.trim($("#edit-noteContent").val());
			//表单验证
			if(noteContent==""){
				alert("备注内容不能为空");
				return;
			}
			//发送请求
			$.ajax({
				url:'workbench/activity/updateClueRemarkByClueId.do',
				data:{
					id:id,
					noteContent:noteContent
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if(data.code=="1"){
						//关闭模态窗口
						$("#editRemarkModal").modal("hide");
						//刷新备注列表
						$("#div_"+data.returnData.id+" h5").text(data.returnData.noteContent);
						$("#div_"+data.returnData.id+" small").text(" "+data.returnData.editTime+" 由${sessionScope.sessionUser.name}修改");
					}else{
						//提示信息
						alert(data.message);
						//模态窗口不关闭
						$("#editRemarkModal").modal("show");
					}
				}
			});
		}

	</script>

</head>
<body>
<!-- 修改线索备注的模态窗口 -->
<div class="modal fade" id="editRemarkModal" role="dialog">
	<%-- 备注的id --%>
	<input type="hidden" id="remarkId">
	<div class="modal-dialog" role="document" style="width: 40%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改备注</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<input type="hidden" id="edit-id">
					<div class="form-group">
						<label for="edit-noteContent" class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea class="form-control" rows="3" id="edit-noteContent"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="updateRemarkBtn">更新</button>
			</div>
		</div>
	</div>
</div>

<!-- 关联市场活动的模态窗口 -->
<div class="modal fade" id="bundModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">关联市场活动</h4>
			</div>
			<div class="modal-body">
				<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
					<form class="form-inline" role="form">
						<div class="form-group has-feedback">
							<input type="text" id="searchActivityTxt" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
							<span class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</form>
				</div>
				<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
					<thead>
					<tr style="color: #B3B3B3;">
						<td><input type="checkbox" id="checkAll"/></td>
						<td>名称</td>
						<td>开始日期</td>
						<td>结束日期</td>
						<td>所有者</td>
						<td></td>
					</tr>
					</thead>
					<tbody id="tBody">
					<%--<tr>
						<td><input type="checkbox"/></td>
						<td>发传单</td>
						<td>2020-10-10</td>
						<td>2020-10-20</td>
						<td>zhangsan</td>
					</tr>
					<tr>
						<td><input type="checkbox"/></td>
						<td>发传单</td>
						<td>2020-10-10</td>
						<td>2020-10-20</td>
						<td>zhangsan</td>
					</tr>--%>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="saveBundActivityBtn">关联</button>
			</div>
		</div>
	</div>
</div>


<!-- 返回按钮 -->
<div style="position: relative; top: 35px; left: 10px;">
	<a href="javascript:void(0);" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
</div>

<!-- 大标题 -->
<div style="position: relative; left: 40px; top: -30px;">
	<div class="page-header">
		<h3>${clue.fullname}${clue.appellation} <small>${clue.company}</small></h3>
	</div>
	<div style="position: relative; height: 50px; width: 500px;  top: -72px; left: 700px;">
		<button type="button" class="btn btn-default" id="convertClueBtn"><span class="glyphicon glyphicon-retweet"></span> 转换</button>

	</div>
</div>

<br/>
<br/>
<br/>

<!-- 详细信息 -->
<div style="position: relative; top: -70px;">
	<div style="position: relative; left: 40px; height: 30px;">
		<div style="width: 300px; color: gray;">名称</div>
		<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.fullname}${clue.appellation}</b></div>
		<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">所有者</div>
		<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.owner}</b></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 10px;">
		<div style="width: 300px; color: gray;">公司</div>
		<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.company}</b></div>
		<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">职位</div>
		<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.job}</b></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 20px;">
		<div style="width: 300px; color: gray;">邮箱</div>
		<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.email}</b></div>
		<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">公司座机</div>
		<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.phone}</b></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 30px;">
		<div style="width: 300px; color: gray;">公司网站</div>
		<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.website}</b></div>
		<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">手机</div>
		<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.mphone}</b></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 40px;">
		<div style="width: 300px; color: gray;">线索状态</div>
		<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.state}</b></div>
		<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">线索来源</div>
		<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.source}</b></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 50px;">
		<div style="width: 300px; color: gray;">创建者</div>
		<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${clue.createBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${clue.createTime}</small></div>
		<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 60px;">
		<div style="width: 300px; color: gray;">修改者</div>
		<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${clue.editBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${clue.editTime}</small></div>
		<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 70px;">
		<div style="width: 300px; color: gray;">描述</div>
		<div style="width: 630px;position: relative; left: 200px; top: -20px;">
			<b>
				${clue.description}
			</b>
		</div>
		<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 80px;">
		<div style="width: 300px; color: gray;">联系纪要</div>
		<div style="width: 630px;position: relative; left: 200px; top: -20px;">
			<b>
				${clue.contactSummary}
			</b>
		</div>
		<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 90px;">
		<div style="width: 300px; color: gray;">下次联系时间</div>
		<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.nextContactTime}</b></div>
		<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px; "></div>
	</div>
	<div style="position: relative; left: 40px; height: 30px; top: 100px;">
		<div style="width: 300px; color: gray;">详细地址</div>
		<div style="width: 630px;position: relative; left: 200px; top: -20px;">
			<b>
				${clue.address}
			</b>
		</div>
		<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
	</div>
</div>

<!-- 备注 -->
<div id="remarkDivList" style="position: relative; top: 40px; left: 40px;">
	<div class="page-header">
		<h4>备注</h4>
	</div>

	<c:forEach items="${remarkList}" var="remark">
		<div class="remarkDiv" id="div_${remark.id}" style="height: 60px;">
			<img title="${remark.createBy}" src="image/user-thumbnail.png" style="width: 30px; height:30px;">
			<div style="position: relative; top: -40px; left: 40px;" >
				<h5>${remark.noteContent}</h5>
				<font color="gray">线索</font> <font color="gray">-</font> <b>${clue.fullname}${clue.appellation}-${clue.company}</b> <small style="color: gray;"> ${remark.editFlag=='0'?remark.createTime:remark.editTime} 由${remark.editFlag=='0'?remark.createBy:remark.editBy}${remark.editFlag=='0'?'创建':'修改'}</small>
				<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
					<a class="myHref" name="editA" remarkId="${remark.id}" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="myHref" name="deleteA" remarkId="${remark.id}" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
				</div>
			</div>
		</div>
	</c:forEach>



	<div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
		<form role="form" style="position: relative;top: 10px; left: 10px;">
			<textarea id="remark" class="form-control" style="width: 850px; resize : none;" rows="2"  placeholder="添加备注..."></textarea>
			<p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
				<button id="cancelBtn" type="button" class="btn btn-default">取消</button>
				<button type="button" id="createClueRemarkBtn" class="btn btn-primary">保存</button>
			</p>
		</form>
	</div>
</div>

<!-- 市场活动 -->
<div>
	<div style="position: relative; top: 60px; left: 40px;">
		<div class="page-header">
			<h4>市场活动</h4>
		</div>
		<div style="position: relative;top: 0px;">
			<table class="table table-hover" style="width: 900px;">
				<thead>
				<tr style="color: #B3B3B3;">
					<td>名称</td>
					<td>开始日期</td>
					<td>结束日期</td>
					<td>所有者</td>
					<td></td>
				</tr>
				</thead>
				<tbody id="relationedTBody">
				<c:forEach items="${activityList}" var="act">
					<tr id="tr_${act.id}">
						<td>${act.name}</td>
						<td>${act.startDate}</td>
						<td>${act.endDate}</td>
						<td>${act.owner}</td>
						<td><a href="javascript:void(0);" activityId="${act.id}"  style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
					</tr>
				</c:forEach>
				<%--<tr>
					<td>发传单</td>
					<td>2020-10-10</td>
					<td>2020-10-20</td>
					<td>zhangsan</td>
					<td><a href="javascript:void(0);"  style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
				</tr>
				<tr>
					<td>发传单</td>
					<td>2020-10-10</td>
					<td>2020-10-20</td>
					<td>zhangsan</td>
					<td><a href="javascript:void(0);"  style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
				</tr>--%>
				</tbody>
			</table>
		</div>

		<div>
			<a href="javascript:void(0);" id="bundActivityBtn" style="text-decoration: none;"><span class="glyphicon glyphicon-plus"></span>关联市场活动</a>
		</div>
	</div>
</div>


<div style="height: 200px;"></div>
</body>
</html>