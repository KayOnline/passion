<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" charset="UTF-8">
<!--
	//combo示例
	$('#dog').combo({   
	    required:true,   
	    editable:false
	});  
	$('#sp').appendTo($('#dog').combo('panel'));
	$('#sp div').click(function() {
	var id = $(this).find('img').attr('id');
	var src = $(this).find('img').attr('src');
	$('#dog').combo('setValue', id).combo('setText', id).combo('hidePanel');
	$('#showImg').replaceWith('<div id="showImg"><img src="' + src + '"/></div>');
	
		}).hover(function() {
		$(this).css('background', '#FBEC88');}, 
	 		function() {
		$(this).css('background', '');}
	);
	
	$('#ccc').combobox({
 		filter: function(q, row){
 			var opts = $(this).combobox('options');
 			return row[opts.textField].indexOf(q) == 0;
 		}
 	});

//-->
</script>




<div style="padding:10px;">
	<span>Your Dog:</span><input id="dog" name="dog">
       <div id="sp">
       	<div><img id="puppy_dogs_01" src="style/images/dogs/puppy_dogs_01.png" alt="puppy_dogs_01"/></div>
       	<div><img id="puppy_dogs_02" src="style/images/dogs/puppy_dogs_02.png" alt="puppy_dogs_02"/></div>
       	<div><img id="puppy_dogs_03" src="style/images/dogs/puppy_dogs_03.png" alt="puppy_dogs_03"/></div>
       	<div><img id="puppy_dogs_04" src="style/images/dogs/puppy_dogs_04.png" alt="puppy_dogs_04"/></div>
       	<div><img id="puppy_dogs_05" src="style/images/dogs/puppy_dogs_05.png" alt="puppy_dogs_05"/></div>
       </div>
       <div id="showImg"></div>
</div>
