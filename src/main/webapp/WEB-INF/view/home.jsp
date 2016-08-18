<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="resources/styles/jqx.base.css" type="text/css" />
	<link rel="stylesheet" href="resources/styles/jqx.ui-lightness.css" type="text/css" />
	<link rel="stylesheet" href="resources/styles/bootstrap.min.css" type="text/css" />
	
	
	<link rel="stylesheet" href="resources/styles/jqx.metro.css" type="text/css" />
	
	<script type="text/javascript" src="resources/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/jqxcore.js"></script>
	<script type="text/javascript" src="resources/js/jqxribbon.js"></script>
	<script type="text/javascript" src="resources/js/jqxwindow.js"></script>
	<script type="text/javascript" src="resources/js/jqxlayout.js"></script>
	<script type="text/javascript" src="resources/js/jqxdockinglayout.js"></script>
	<script type="text/javascript" src="resources/js/jqxdata.js"></script>
    <script type="text/javascript" src="resources/js/jqxbuttons.js"></script>
    <script type="text/javascript" src="resources/js/jqxscrollbar.js"></script>
    <script type="text/javascript" src="resources/js/jqxlistbox.js"></script>
    <script type="text/javascript" src="resources/js/jqxmenu.js"></script>
    <script type="text/javascript" src="resources/js/jqxgrid.js"></script>
    <script type="text/javascript" src="resources/js/jqxgrid.selection.js"></script>
    <script type="text/javascript" src="resources/js/jqxgrid.columnsresize.js"></script>
	<script type="text/javascript" src="resources/js/jqxgrid.filter.js"></script>
    <script type="text/javascript" src="resources/js/jqxgrid.sort.js"></script>
	 <script type="text/javascript" src="resources/js/jqxdropdownlist.js"></script>
	<script type="text/javascript" src="resources/js/jqxcheckbox.js"></script>
	<script type="text/javascript" src="resources/js/jqxgrid.edit.js"></script>
	<script type="text/javascript" src="resources/js/jqxnotification.js"></script>
	 <script type="text/javascript" src="resources/js/jqxnavbar.js"></script>
	 <script type="text/javascript" src="resources/js/jqxloader.js"></script>
	<title>网院电子书</title>
	<style type="text/css">
		.form-control {
					display:inline;								
				}
		.NavBarTitle {
					text-align:center;
					font-size: 20px;
					margin: auto;					
				}
				.NavBar {					
					margin: auto;					
				}
				#TeachingplanSearchButton{
					width:90%;
					position:absolute;
					bottom:15px;
					left:8px;				
				}
				
			input[value='无']{
				color:#878e8e;
			}
				
	</style>
</head>

<body>
		<!--主布局-->
		<div style="position:relative;margin-left: auto;margin-right: auto;margin-top: 0px;width:1350px;height:800px;">
			<div id='CollegeListBox' style="float:left;"></div>
			<div id="jqxDockingLayout" style="float:left;">
				<!--autoHideGroup-->
				 <div data-container="TeachingPlanBatchPanel">
						<div class="NavBarTitle">年份</div>					
						 <div id="yearNavBar" class="NavBar">
								<ul>
									<li>2014年</li>
									<li>2015年</li>
									<li>2016年</li>
								</ul>
						</div>
						<div class="NavBarTitle">季度</div>		
						<div id="semesterNavBar" class="NavBar">
								<ul>
									<li>春季</li>
									<li>秋季</li>								
								</ul>
						</div>
						<div class="NavBarTitle">层级</div>		
						<div id="levelNavBar" class="NavBar">
								<ul>
									<li>高起专</li>
									<li>高起本</li>
									<li>专升本</li>								
								</ul>
						</div>
						<button class="btn btn-default" id="TeachingplanSearchButton">查询</button>
				 </div>
				 <div data-container="CourseListPanel"></div>
				 <!--documentGroup-->
				 <div data-container="CourseEbookListPanel">
					<div id="collegeCourseGrid" ></div>
				 </div>
				 <!--bottom tabbedGroup-->
				 <div data-container="EbookInfoListPanel">
					<div id="courseEbookGrid" ></div>
				 </div>
			</div>
		</div>	

		
		<div id="myShow" style="display:none;">
        	<img alt="loading" src="resources/image/wait.gif" />
    	</div>
		
		<div id="container" style="position:relative;margin-left: auto;margin-right: auto;width: 1350px; height:100px; "></div>
		<div id="jqxNotification">
			<div id="notificationContent">
			</div>
		</div>
		
		<div id="popupWindow" >
                <div>
                    <img width="14" height="14" src="resources/styles/images/warning.png" alt="" />????
				</div>
                <div style="overflow: hidden;">  
					<div >
                        <span style="font-family: '微软雅黑';font-size: 15px;">确认要删除所选课程吗?</span>
                    </div>				
                    <div>
						<div style="float: right; margin-top: 50px;margin-right: 50px;">
							<input type="button" id="ok" value="删除" style="margin-right: 10px" />
							<input type="button" id="cancel" value="取消" />
						</div>
                    </div>
                </div>
        </div>
		
		<!-- 添加电子书弹出窗口-->
		<div class="modal fade" id="addEbookModal"  tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
			  <div class="modal-content" style='width:800px' >
				 <div class="modal-header">
					<button type="button" class="close" 
					   data-dismiss="modal" aria-hidden="true">
						  &times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
					<span style="font-family: '微软雅黑';font-size: 15px;"> 电子书</span>				  
					</h4>
				 </div>
				 <div class="modal-body" id='searchEbookPanel'>
				 		<form class="form-search" style="margin-bottom:7px" id="addEbookForm">
							  <input type="text" class="form-control" style="width:100px" placeholder="SSId查询"  name="SSId">
							  <input type="text" class="form-control" style="width:302px" placeholder="书名查询" name="bookName">
							  <input type="text" class="form-control" style="width:90px" placeholder="作者查询" name="author">
							  <input type="text" class="form-control" style="width:130px" placeholder="出版社查询" name="publisher">
							  <button  id="searchEbookButtion" class="btn btn-primary" style="width:110px">查询</button>
						</form>
					<div id="ebookGrid" ></div>
				 </div>
				 <div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="ConfirmAddEbookButton">确认</button>
				 </div>
			  </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

</body>
	<script>
	$(document).ready(function(){
	

	
	//当前页面状态变量
	var currentCourseSelect;
	var lastQueryCollege;
	var courseEbookGridStatus;
	
	
	 $('#popupWindow').jqxWindow({
		//position: { x: offset.left + 50, y: offset.top + 50} ,
		maxHeight: 150, maxWidth: 280, minHeight: 30, minWidth: 250, height: 145, width: 270,
		resizable: false, isModal: true, modalOpacity: 0.3,
		okButton: $('#ok'), cancelButton: $('#cancel'),
		autoOpen: false,
		initContent: function () {
			$('#ok').jqxButton({ width: '65px' });
			$('#cancel').jqxButton({ width: '65px' });
			$('#cancel').focus();
		}
	});					
	
	  var layout=[{
					type:'layoutGroup',
					orientation:'horizontal',
					items:[{
								type:'autoHideGroup',
								alignment:'left',
								width:80,
								unpinnedWidth:200,
							    items:[{
										type: 'layoutPanel',
										title: '课程批次',
										contentContainer: 'TeachingPlanBatchPanel'
									  },{
										type: 'layoutPanel',
										title: '其他',
										contentContainer: 'CourseListPanel'
									  }]
							},{
								type:'layoutGroup',
								orientation:'vertical',
								width:1120,
								items:[{
										type:'documentGroup',
										height:550,
										minHeight:100,
										items:[{
												type:'documentPanel',
												title:'教学计划',
												contentContainer:'CourseEbookListPanel',
												initContent: function () {
													 $("#yearNavBar").jqxNavBar({ height: 120, orientation: 'vertical' ,theme:'ui-lightness',width: '90%'});
													 $("#semesterNavBar").jqxNavBar({ height:80, orientation: 'vertical' ,theme:'ui-lightness',width: '90%'});
													 $("#levelNavBar").jqxNavBar({ height: 120, orientation: 'vertical' ,theme:'ui-lightness',width: '90%'});
												}
												}]
									  },{									
										type:'tabbedGroup',
										height:250,
										items:[{
												type:'layoutPanel',
												title:'电子书',
												contentContainer:'EbookInfoListPanel'
											   }]			
									  }],
							}]
					}]
		
	 $('#jqxDockingLayout').jqxDockingLayout({ width: 1200, height: '800', layout: layout,theme: 'metro',});
		
		
		//加载学校列表
		var collegeData={
			   dataType:"json",
			   datafields:[
					{name:'id'},
					{name:'collegeName'},
					{name:'url'},
					{name:'code'}
			   ],
			   id:'id',
			   url:'<%=request.getContextPath()%>/GetAllCollege'
		    }	
		var dataAdapter = new $.jqx.dataAdapter(collegeData);
		$("#CollegeListBox").jqxListBox({ source: dataAdapter, displayMember: "collegeName", valueMember: "id", width: 130, height: 800, theme: 'ui-lightness' });	
					
	//获取选择的学校信息
	    var getSelectCollegeData= function(){
			var collegeData = $('#CollegeListBox').jqxListBox('source').records;
			var size= $('#CollegeListBox').jqxListBox('source').totalrecords; 			
			for(var i=0;i<size;i++){
				if(collegeData[i].id==lastQueryCollege.value){			
					return collegeData[i];
				}
			}	
		}	
		
		
		$("#CollegeListBox").bind('dblclick',function(event){
					//var args = event.args;
					//var item = $('#CollegeListBox').jqxListBox('getItem', args.index);
					$('#collegeCourseGrid').jqxGrid('clear');
					lastQueryCollege=$("#CollegeListBox").jqxListBox('getSelectedItem');
					ajaxQuery(getSelectCollegeData().url,1,getSelectCollegeData().code);
			});	
	                   
		
			
		//获取选择的教学计划批次
		var getSelectBatch=function(){
			var year = $("#yearNavBar").jqxNavBar('getSelectedIndex'); 
			switch (year){
				case 0:
					year='14';
					break;
				case 1:
					year='15';
					break;
				case 2:
					year='16';
					break;}
			var semester = $("#semesterNavBar").jqxNavBar('getSelectedIndex');
				switch (semester){
					case 0:
						semester='1';
						break;
					case 1:
						semester='3';
						break;}
			var batch=year+semester;
			var level = $("#levelNavBar").jqxNavBar('getSelectedIndex');	
			switch (level){
				case 0:
					level='高起专';
					break;
				case 1:
					level='高起本';
					break;
				case 2:
					level='专升本';
					break;}
			var requstJson="{'CollegeUrl':'"+getSelectCollegeData().url+"','Batch':'"+batch+"','Level':'"+level+"'}";
			return requstJson;	
			}
		
		//教学计划查询,加载CollegeCourseGrid
		$("#TeachingplanSearchButton").click(function(){
			lastQueryCollege=$("#CollegeListBox").jqxListBox('getSelectedItem');
			$('#collegeCourseGrid').jqxGrid('clear');
			ajaxQuery(getSelectBatch(),2,getSelectCollegeData().code);
		});	
		
		
		var ajaxQuery=function(requestString,type,code){
	
			$.ajax({
					url:'<%=request.getContextPath()%>/GetTeachingPlan?para='+requestString+'&type='+type+'&code='+code, 
					type:"get", 
					dataType:"json",
		
					error:function (data) {  
							rtnObj = data.responseText;  
						}, 
					beforeSend:function (XMLHttpRequest) {  
							$("#myShow").css({display:"",position:"absolute",top:"35%",left:"55%",margin:"-60px 0px 0px -169px"})
						}, 
					complete:function (XMLHttpRequest, textStatus) {  
						}, 
					success:function (data) { 
						$("#myShow").css({display:"none"});
							if(data.errorMessage!=null && data.errorMessage!=''){
								alert(data.errorMessage);
							}
						
							 initCollegeCourseGrid(data.data,code);		
						}});		
		}
	  			

		//启动学校教学计划课程数据表格
		 var initCollegeCourseGrid = function (teachingplanData,code) {
				resetCourseEbookGrid(courseEbookGridStatus);
			
				var collegeCourseData={
						dataType:'json',
						datafields: [
							{ name: 'majorId' },
							{ name: 'majorName' },
							{ name: 'level' },
							{ name: 'courseId' },
							{ name: 'courseName' },
							{ name: 'semester' },
							{ name: 'courseWareStatus',type: 'bool' },
						],
						id:'courseId',
						localdata:teachingplanData
					};
				var collegeCourseDataAdapter = new $.jqx.dataAdapter(collegeCourseData);
				
				var cellsrenderer =function(row, columnfield, value, defaulthtml, columnproperties,entire ){		
					if(entire.courseWareStatus){
						return "查看";
					}
					if(!entire.courseWareStatus){				
						return"无"
					}
				}
				
				$("#collegeCourseGrid").jqxGrid({
												source: collegeCourseDataAdapter,
												theme: 'ui-lightness',
												width:'99.5%',
												height:'99%',
												altrows: true,
												showfilterrow: true,
												sortable: true,
												filterable: true,
												
												columnsresize:true,
												columns:[
													{ text: '专业', datafield: 'majorName',filtertype: 'input',width:150},
													{ text: '层级', datafield: 'level',filtertype: 'input',width:100,cellsalign: 'center'},
													{ text: '课程Id', datafield: 'courseId',filtertype: 'input',width:80,cellsalign: 'center'},
													{ text: '课程', datafield: 'courseName',filtertype: 'input',cellsalign: 'center'},
													{ text: '可用', datafield: 'courseWareStatus',filtertype: 'bool',width:50,cellsalign: 'center',columntype: 'checkbox'},										
													{ text: '课件',width:50,cellsalign: 'center',columntype: 'button',cellsrenderer: cellsrenderer,
															buttonclick: function (row) {	 
																													
																if(collegeCourseDataAdapter.records[row].courseWareStatus){
																	window.open("http://7u2k2m.com2.z0.glb.qiniucdn.com/static/index.html?College="+getSelectCollegeData().code+"&CourseId="+getSelectCollegeCourseData().courseId);
																}
															 }													
													},	
													{ text: '学期', datafield: 'semester',filtertype: 'checkedlist',width:50,cellsalign: 'center'},														
													{ text: '删除', columntype: 'button',width:80,hidden:true,cellsrenderer: function () {return "编辑";},
															buttonclick: function (row) {
																 // open the popup window when the user clicks a button.
																 editrow = row;
																 var offset = $("#collegeCourseGrid").offset();
																 																																																									
																$("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 350, y: parseInt(offset.top) + 225 } });
																$("#popupWindow").jqxWindow('open');
															 }
													 }
												]
												});													
		};											
	
		//获取选择的课程信息
		var getSelectCollegeCourseData=function(){
			var selectionCollegeCourseRowIndex=$("#collegeCourseGrid").jqxGrid("getselectedrowindex");
			var collegeCourseRowData=$("#collegeCourseGrid").jqxGrid("getrowdata",selectionCollegeCourseRowIndex);
			return collegeCourseRowData;
		}
		
		//学校课程数据表格单击事件
				$("#collegeCourseGrid").bind('cellclick',function(event){
					var rowindex=event.args.rowindex;
					if(rowindex==currentCourseSelect){
					}else if(event.args.datafield!='delete'){
						currentCourseSelect=rowindex;		
						var bounddata=event.args.row.bounddata;
						initCourseEbookGrid(getSelectCollegeData().id,bounddata.courseId,bounddata.courseName);					
					}						
			})
			
		
			var initCourseEbookGrid =function(collegeId,courseId,courseName){
			courseEbookGridStatus=true;
			  var courseEbookData={
						datatype: "json",
						datafields: [
							{ name: 'id' },
							{ name: 'ssid' },
							{ name: 'dxid' },
							{ name: 'bookName' },
							{ name: 'author' },
							{ name: 'publisher' },
							{ name: 'bookPath' },
							{ name: 'coverImgPath' },
							{ name: 'bookStatus' },
						],
						id:'id',
						url:'<%=request.getContextPath()%>/GetCourseEbook?collegeId='+collegeId+'&courseId='+courseId+'&courseName='+courseName
					};
				var ebookDataAdapter = new $.jqx.dataAdapter(courseEbookData);
				$("#courseEbookGrid").jqxGrid({
					source: ebookDataAdapter,
					width:'99.5%',
					height:'99%',
					theme: 'ui-lightness',
					altrows: true,
					
					columnsresize:true,
					showstatusbar: true,
					statusbarheight:30,
					renderstatusbar: function (statusbar) {
						// appends buttons to the status bar.
						var container = $("<div style='overflow: hidden; position: relative; margin: 5px;'></div>");
						var addButton = $("<div style='float: left; margin-left: 5px;'><span style='margin-left: 4px; position: relative; top: -3px;'>添加</span></div>");												
						var deleteButton = $("<div style='float: left; margin-left: 5px;'><span style='margin-left: 4px; position: relative; top: -3px;'>删除</span></div>");
						var reloadButton = $("<div style='float: left; margin-left: 5px;'><span style='margin-left: 4px; position: relative; top: -3px;'>详细</span></div>");
						
						container.append(addButton);
						container.append(deleteButton);
						container.append(reloadButton);
						
						statusbar.append(container);
						addButton.jqxButton({  width: 60, height: 15 });						
						deleteButton.jqxButton({  width: 65, height: 15 });
						reloadButton.jqxButton({  width: 65, height: 15 });
						
						 // add new CourseEbook.
						addButton.click(function (event) {
							$('#addEbookModal').modal();
						});
						deleteButton.click(function (event) {
						
							var selectionCollegeCourseRowIndex=$("#collegeCourseGrid").jqxGrid("getselectedrowindex");
							var row1=$("#collegeCourseGrid").jqxGrid("getrowdata",selectionCollegeCourseRowIndex);	
							
							var selectionCourseEbookRowIndex=$("#courseEbookGrid").jqxGrid("getselectedrowindex");
							var row2=$("#courseEbookGrid").jqxGrid("getrowdata",selectionCourseEbookRowIndex);
							
							var collegeCourseEbook={collegeId:lastQueryCollege,courseId:row1.courseId,SSId:row2.ssid};							
	 						
							$.ajax({
								url:'<%=request.getContextPath()%>/DeleteCollegeCourseEbook', 
								type:"post", 
								data:collegeCourseEbook, 
								async:false, 
								dataType:"json", 
								cache:false, 
								error:function (data) {  
										rtnObj = data.responseText;  
									}, 
								beforeSend:function (XMLHttpRequest) {  
										//ajaxStart();  
									}, 
								complete:function (XMLHttpRequest, textStatus) {  
										//ajaxComplete();  
									}, 
								success:function (data) { 								
									if(data>0){					
										$("#jqxNotification").jqxNotification({ width: "100%", appendContainer: "#container", opacity: 0.9, autoClose: true, template: "success" });
										$("#notificationContent").html(	"<Strong>删除成功!</Strong>  共删除该课程"+data+" 本电子书");																	
										var selectCollegeCourseRow=$("#collegeCourseGrid").jqxGrid('getrowdata', currentCourseSelect);
										initCourseEbookGrid(selectCollegeCourseRow.id);																			
									}else{
										$("#jqxNotification").jqxNotification({ width: "100%", appendContainer: "#container", opacity: 0.9, autoClose: true, template: "error" });
										$("#notificationContent").html("<Strong>删除失败......</Strong>");
									}
									$("#jqxNotification").jqxNotification("open");
									
									
								}
							});
						
						});
					},
					columns:[
						{ text: 'SSId', datafield: 'ssid',width:120},
						{ text: 'DXId', datafield: 'dxid',width:180,cellsalign: 'center'},
						{ text: '书名', datafield: 'bookName',cellsalign: 'center'},
						{ text: '作者', datafield: 'author',cellsalign: 'center'},
						{ text: '出版社', datafield: 'publisher',cellsalign: 'center'},
						{ text: '状态', datafield: 'bookStatus',cellsalign: 'center',width:50},						
					]
					});
			}
		
		$("#searchEbookButtion").click(function(){
				$('#addEbookForm').submit(function() {
					jQuery.ajax({
						url: '<%=request.getContextPath()%>/GetEbook',
						data: $('#addEbookForm').serialize(),
						type: "POST",
						beforeSend: function()
						{		
						},
						success: function(res)
						{
							initEbookGrid(res);
						}
					});
						return false;
				});
		});	
	
	//确认添加电子书按钮单击事件
		$("#ConfirmAddEbookButton").click(function(){
			var courseData=getSelectCollegeCourseData();
			var collegeIdData=getSelectCollegeData().id;
			
			var collegeCourseEbookList=new Array();
			var gridData=$('#ebookGrid').jqxGrid('getboundrows');
			var paras='';
			var i=0;
			 $.each(gridData,function(key,val){
						if(val.select){
						var collegeId='ccelist['+i+'].collegeId='+collegeIdData;
						var courseId='ccelist['+i+'].courseId='+courseData.courseId;
						var courseName='ccelist['+i+'].courseName='+courseData.courseName;
						var bookName='ccelist['+i+'].bookName='+val.bookName;
						var SSId='ccelist['+i+'].SSId='+val.ssid;						
											
						paras=paras+collegeId+'&'+courseId+'&'+courseName+'&'+bookName+'&'+SSId+'&';
						i++;
						 }			
					 });					 
			alert(paras);
			$.ajax({
			url:'<%=request.getContextPath()%>/AddCollegeCourseEbook?'+paras, 
			type:"get", 
			data:paras, 
			async:false, 
			cache:false, 
			error:function (data) {  
					rtnObj = data.responseText;  
				}, 
			beforeSend:function (XMLHttpRequest) {  
					//ajaxStart();  
				}, 
			complete:function (XMLHttpRequest, textStatus) {  
					//ajaxComplete();  
				}, 
			success:function (data) {  
				
					$('#addEbookModal').modal('toggle');
					$("#jqxNotification").jqxNotification({ width: "100%", appendContainer: "#container", opacity: 0.9, autoClose: true, template: "success" });
					$("#notificationContent").html(	"添加成功!<Strong>  该课程添加"+data+"  </Strong>本电子书");
					$("#jqxNotification").jqxNotification("open");
					
					var selectCollegeCourseRow=$("#collegeCourseGrid").jqxGrid('getrowdata', currentCourseSelect);
					initCourseEbookGrid(selectCollegeCourseRow.id);
				}});
			
		})
	})
	</script>
	
	<script>
	
			
	
		var resetCourseEbookGrid=function(status){
			if(status){
				$('#courseEbookGrid').jqxGrid('clear');
				$('#courseEbookGrid').jqxGrid({ showstatusbar: false});		
			}
		}
	
		
		 var initEbookGrid = function (datas) {	
				var ebookData={
						datatype: "json",
						datafields: [
							{ name: 'id' },
							{ name: 'ssid' },
							{ name: 'bookName' },
							{ name: 'author' },
							{ name: 'publisher' },
							{ name: 'bookStatus' },
							{ name: 'select',type: 'bool'},
						],
						id:'id',
						localdata:datas
					};
				var ebookDataAdapter = new $.jqx.dataAdapter(ebookData);
				$("#ebookGrid").jqxGrid({
												source: ebookDataAdapter,
												theme: 'ui-lightness',
												width:'99.5%',
												height:'99%',
												showfilterrow: true,
												sortable: true,
												filterable: true,
												editable: true,
												
												columnsresize:true,
												columns:[
													{ text: '电子书ID', datafield: 'id',hidden: true,editable:false},
													{ text: 'SSId', datafield: 'ssid',filtertype: 'input',width:100,cellsalign: 'left',editable:false},
													{ text: '书名', datafield: 'bookName',filtertype: 'input',cellsalign: 'left',editable:false},
													{ text: '作者', datafield: 'author',filtertype: 'input',width:100,cellsalign: 'left',editable:false},
													{ text: '出版社', datafield: 'publisher',filtertype: 'input',width:130,cellsalign: 'left',editable:false},
													{ text: '状态', datafield: 'bookStatus',filtertype: 'checkedlist',width:50,cellsalign: 'center',editable:false},
													{ text: '选择', datafield: 'select',filtertype: 'bool', columntype: 'checkbox', width: 50 }													
												]
												});
		
		};
		
		function a(obj){    
			var names="";    

				for(var name in obj){       
				   names+=name+": "+obj[name]+", ";  
				}  
				console.log(names);  
		}  
	</script>
</html>