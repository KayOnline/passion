<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<script type="text/javascript">
	var westTree = undefined;
	$(function() {
 		westTree = $('#layout_west_tree').tree({  
			url : '${pageContext.request.contextPath}/menuController/query',
			lines : true,
			animate : true,
			onDblClick : function(node) {
				if (centerTab.tabs('exists', node.text)) {
					centerTab.tabs('select', node.text);
				} else {
					
					// Define a new tabOptions
					var tabOptions = {
						title: node.text,
						closable: true,
						fit: true,
						iconCls: node.iconCls,
						//content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>',
						//href : url,
						tools:[{   
					        iconCls:'icon-mini-refresh',   
					        handler:function(){
								var tab = centerTab.tabs('getTab', node.text);  // get selected panel
								centerTab.tabs('update', {
									tab: tab,
									options: tab.panel('options')
								});
					        }   
					    }]		
					};
					
					var url = kay.basePath() + '/error.jsp';
					
					if (node.attributes.url && node.attributes.url.length > 0) {
						
						// Outer link
						if(node.attributes.url.indexOf('http') != -1) {
							
							url = node.attributes.url;
							tabOptions.content = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>';
							
						} else {
							
							url = kay.basePath() + node.attributes.url;
							console.info(url);
							//tabOptions.href = url;
							tabOptions.content = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>';
							
						}
						
					} else {
						
						tabOptions.href = url;
						
					}
					
					centerTab.tabs('add', tabOptions);
					
				}
			},
			onLoadSuccess: function(node, data) {	
				westTree.tree('expandAll');//默认初始化时候全部展开
			}
		});
     });
 </script>
	

<div id="accordion" class="easyui-accordion" data-options="fit:true,border:false">  
    <div title="系统菜单" style="overflow-x:hidden" >
		<ul id="layout_west_tree"></ul>
	</div>  
    <div title="XX管理" style="overflow-x:hidden" >  
        content2   
    </div>  
    <div title="XX管理" style="overflow-x:hidden">  
        content3   
    </div>  
</div>  

