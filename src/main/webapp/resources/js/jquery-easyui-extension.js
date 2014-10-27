

/*function setDropListFieldOptions() {
	        
	// Get all column fields
	var unfrozenColumnFields = $(this).datagrid('getColumnFields');
	var frozenColumnFields = $(this).datagrid('getColumnFields',true);
	var columnFields = [].concat(unfrozenColumnFields, frozenColumnFields);
	
	for(var i=0; i<columnFields.length; i++) {
		console.info(columnFields[i]);
		
		// Get option for each columns fields
		var columnOption = $(this).datagrid('getColumnOption', columnFields[i]);
		
		// Deal with combobox
		if(columnOption.editor != undefined && columnOption.editor.type == 'combobox') {
			
			// Note: 'this.editor.options.data' mean invoker's data
			columnOption.formatter = function(value, row, index) {
				var data = this.editor.options.data;
				for(var i=0; i<data.length; i++) {
					if(value == data[i].VALUE) {
						return data[i].LABEL;
					}
				}
				return value;
			}
			
			if(columnOption.editor.options) {
				// Set uniform property	
				columnOption.editor.options.valueField = 'VALUE';  
			    columnOption.editor.options.textField = 'LABEL';
			    columnOption.editor.options.editable = false;
			    columnOption.editor.options.panelHeight ='auto';
			}
		}
		
		// Deal with combobox
		if(columnOption.editor != undefined && columnOption.editor.type == 'combotree') {
			columnOption.formatter = function(node) {
				console.info(node);
				return node;
			}
		}
	}	
}*/



/*Create Global Object*/
var kay = $.extend({},kay);


(function($) {
	/*
	 * #Descsription
	 * 用于DataGrid收集CUD数据
	 * #Usage
	 * var data = datagrid.datagrid('collectData');
	 */
	$.fn.datagrid.methods.collectData = function(datagrid) {
		var data = {};
		
		var inserted = datagrid.datagrid('getChanges','inserted');		// session.save
		var updated = datagrid.datagrid('getChanges','updated');		// session.update
		var deleted = datagrid.datagrid('getChanges','deleted');		// session.delete
		
		data.inserted = JSON.stringify(inserted);
		data.updated = JSON.stringify(updated);
		data.deleted = JSON.stringify(deleted);
		return data;
	};
})(jQuery);

/*
 * #Descsription
 * 用于编辑DataGrid单元格，须要绑定onClickCell事件
 * #Usage
 * $('#dg').datagrid({
 *     onClickCell : beginEdit
 * });
 */
function beginEdit(rowIndex, field, value) {
	// Store Datagrid instance
	var $dg = $(this);
	// End editing rows
	var $editingTrs = $('div.panel.datagrid div.datagrid-view2 div.datagrid-body>table tr.datagrid-row-editing');
	$editingTrs.each(function(index, item) {
		var idx = $(item).attr('datagrid-row-index');
		$dg.datagrid('endEdit', parseInt(idx, 10));
	});
	// Call edit cell
	$dg.datagrid('editCell', {
		rowIndex : rowIndex,
		field : field
	});
}

/*
 * #Description
 * 扩展DataGrid的editCell方法
 * #Usage
 * $dg.datagrid('editCell', {
 *     rowIndex : rowIndex,
 *	   field : field
 * });
 */
$.extend($.fn.datagrid.methods, {
	editCell : function(jq, param) {
		return jq.each(function() {
			// get unfrozen columns
			var unfrozenColumns = $(this).datagrid('getColumnFields');	
			// get frozen columns
			var frozenColumns = $(this).datagrid('getColumnFields', true); 
			// all columns
			var columns = unfrozenColumns.concat(frozenColumns);
			for ( var i = 0; i < columns.length; i++) {
				// Return the specified column option.
				var option = $(this).datagrid('getColumnOption', columns[i]);
				// Backup option's editor property
				option.editorTmp = option.editor;
				if (columns[i] != param.field) {
					option.editor = null;
				}
			}
			// Begin editing a row.
			$(this).datagrid('beginEdit', param.rowIndex);
			for ( var i = 0; i < columns.length; i++) {
				var option = $(this).datagrid('getColumnOption', columns[i]);
				option.editor = option.editorTmp;
			}
		});
	}
});
// $.parser.auto = false;
// $(function() {
// $.messager.progress({
// text : '椤甸潰鍔犺浇涓�...',
// interval : 100
//	});
//	$.parser.parse(window.document);
//	window.setTimeout(function() {
//		$.messager.progress('close');
//		if (self != parent) {
//			window.setTimeout(function() {
//				try {
//					parent.$.messager.progress('close');
//				} catch (e) {
//				}
//			}, 500);
//		}
//	}, 1);
//	$.parser.auto = true;
//});


//$.fn.tree.defaults.loadFilter = function (data, parent) {
//	var opt = $(this).data().tree.options;
//	var idFiled,
//	textFiled,
//	parentField;
//	if (opt.parentField) {
//		idFiled = opt.idFiled || 'id';
//		textFiled = opt.textFiled || 'text';
//		parentField = opt.parentField;
//		
//		var i,
//		l,
//		treeData = [],
//		tmpMap = [];
//		
//		for (i = 0, l = data.length; i < l; i++) {
//			tmpMap[data[i][idFiled]] = data[i];
//		}
//		
//		for (i = 0, l = data.length; i < l; i++) {
//			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
//				if (!tmpMap[data[i][parentField]]['children'])
//					tmpMap[data[i][parentField]]['children'] = [];
//				data[i]['text'] = data[i][textFiled];
//				tmpMap[data[i][parentField]]['children'].push(data[i]);
//			} else {
//				data[i]['text'] = data[i][textFiled];
//				treeData.push(data[i]);
//			}
//		}
//		return treeData;
//	}
//	return data;
//};

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js
 * 
 * @function 
 * 		Prevent from panel/window/dialog beyond browser's boundary
 *
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * 
 * @function 
 * 		Get project's basePath
 * 
 * @example 
 * 		console.info(kay.basePath)
 */
kay.basePath = function() {
	var curWinPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWinPath.indexOf(pathName);
	var localhostPatht = curWinPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPatht + projectName);
};

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js
 * 
 * @function 
 * 		Serialize Form's elements into a JSON Object(not JSON Array)
 * 
 * @example 
 * 		kay.serializeObject($('#form1'))
 */
kay.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js 
 *      jquery.cookie.js
 * 
 * @function 
 * 		modify Easyui theme
 * 
 * @example 
 * 		<%-- EasyUI --%>
 *		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/${cookie.easyuiThemeName.value==null ? 'default' : cookie.easyuiThemeName.value}/easyui.css">  
 *		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">  
 *		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script> 
 *		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script> 
 *		
 *		kay.changeTheme('default');
 *		kay.changeTheme('gray');
 *		kay.changeTheme('metro');
 *		kay.changeTheme('cupertino');
 *		kay.changeTheme('dark-hive');
 *		kay.changeTheme('pepper-grinder');
 *		kay.changeTheme('sunny');
 */
kay.changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframes = $('#iframe');
	if($iframes.length > 0) {
		for(var i=0; i<$iframes.length; i++) {
			$iframes[i].contents().find("#easyuiTheme").attr('href', href);
		}
	}

	//using jquery cookie plugin to cache the themeName
	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js
 * 
 * @function 
 * 		Extend Datagrid methods addEditor and removeEditor
 *		to modify editor property dynamically 
 * 
 * @returns object
 */
$.extend($.fn.datagrid.methods, {
	addEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item.field);
				e.editor = item.editor;
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param.field);
			e.editor = param.editor;
		}
	},
	removeEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item);
				e.editor = {};
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param);
			e.editor = {};
		}
	}
});

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js
 * 
 * @function 
 * 		extend editor property datetimebox
 * 
 * @example 
 * 		none
 */
$.extend($.fn.datagrid.defaults.editors, {   
    datetimebox: {   
        init: function(container, options){   
            var input = $('<input />').appendTo(container); 
            input.datetimebox(options);  
            return input;   
        },   
        getValue: function(target){   
            return $(target).datetimebox('getValue');
        },   
        setValue: function(target, value){   
        	console.info(value);
            $(target).datetimebox('setValue', value);
        },   
        resize: function(target, width){   
            $(target).datetimebox('resize', width);   
        },
        destroy: function(target) {
			$(target).datetimebox('destroy');
	    } 
    }   
});

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js
 * 
 * @function 
 * 		extend editor property comboimage
 * 
 * @returns object
 */
$.extend($.fn.datagrid.defaults.editors, {   
	comboimage: {   
        init: function(container, options){   
            var input = $('<input />').appendTo(container); 
            input.combo(options);
            options.sp.appendTo(input.combo('panel')).show();
            options.sp.find('div').click(function() {
            	var v = $(this).find('img').attr('id');
            	input.combo('setValue', v).combo('setText', v).combo('hidePanel');
	        }).hover(function() {
				$(this).css('background', '#FBEC88');}, 
	 	 		function() {
				$(this).css('background', '');}
	 		);
            return input;   
        },   
        getValue: function(target){   
            return $(target).combo('getValue');
        },   
        setValue: function(target, value){   
            $(target).combo('setValue', value);
        },   
        resize: function(target, width){   
            $(target).combo('resize', width);   
        },
        destroy: function(target) {
			$(target).combo('destroy');
	    } 
    }   
});

  

/**
 * @author Kay
 * 
 * @requires jquery.js
 * 
 * @function To check password and retype password are same
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '瀵嗙爜涓嶄竴鑷达紒'
	}
});

/**
 * @author Kay
 * 
 * @requires 
 * 		jquery.js
 * 
 * @function 
 * 		Easyui Component:datagrid/treegrid/tree/combogrid/combobox/form 
 * 		when component load error, it will show error message
 * 
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('error', XMLHttpRequest.responseText);
};

$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;


/**
 * @author 
 * 		Steven Levithan 
 * 		<http://blog.stevenlevithan.com/archives/date-time-format/comment-page-6#comment-222850>
 * 
 * @function 
 * 		Accepts a date, a mask, or a date and a mask.
 * 		Returns a formatted version of the given date.
 * 
 * @returns 
 * 		a formatted version of the given date.
 * 
 * @example
 * 		var date = new Date();
 * 		date.format('yyyy-mm-dd HH:MM:ss');
 */
var dateFormat = function () {
	var	token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
		timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
		timezoneClip = /[^-+\dA-Z]/g,
		pad = function (val, len) {
			val = String(val);
			len = len || 2;
			while (val.length < len) val = "0" + val;
			return val;
		};

	// Regexes and supporting functions are cached through closure
	return function (date, mask, utc) {
		var dF = dateFormat;

		// You can't provide utc if you skip other args (use the "UTC:" mask prefix)
		if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
			mask = date;
			date = undefined;
		}

		// Passing date through Date applies Date.parse, if necessary
		date = date ? new Date(date) : new Date;
		if (isNaN(date)) throw SyntaxError("invalid date");

		mask = String(dF.masks[mask] || mask || dF.masks["default"]);

		// Allow setting the utc argument via the mask
		if (mask.slice(0, 4) == "UTC:") {
			mask = mask.slice(4);
			utc = true;
		}

		var	_ = utc ? "getUTC" : "get",
			d = date[_ + "Date"](),
			D = date[_ + "Day"](),
			m = date[_ + "Month"](),
			y = date[_ + "FullYear"](),
			H = date[_ + "Hours"](),
			M = date[_ + "Minutes"](),
			s = date[_ + "Seconds"](),
			L = date[_ + "Milliseconds"](),
			o = utc ? 0 : date.getTimezoneOffset(),
			flags = {
				d:    d,
				dd:   pad(d),
				ddd:  dF.i18n.dayNames[D],
				dddd: dF.i18n.dayNames[D + 7],
				m:    m + 1,
				mm:   pad(m + 1),
				mmm:  dF.i18n.monthNames[m],
				mmmm: dF.i18n.monthNames[m + 12],
				yy:   String(y).slice(2),
				yyyy: y,
				h:    H % 12 || 12,
				hh:   pad(H % 12 || 12),
				H:    H,
				HH:   pad(H),
				M:    M,
				MM:   pad(M),
				s:    s,
				ss:   pad(s),
				l:    pad(L, 3),
				L:    pad(L > 99 ? Math.round(L / 10) : L),
				t:    H < 12 ? "a"  : "p",
				tt:   H < 12 ? "am" : "pm",
				T:    H < 12 ? "A"  : "P",
				TT:   H < 12 ? "AM" : "PM",
				Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
				o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
				S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
			};

		return mask.replace(token, function ($0) {
			return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
		});
	};
}();

// Some common format strings
dateFormat.masks = {
	"default":      "ddd mmm dd yyyy HH:MM:ss",
	shortDate:      "m/d/yy",
	mediumDate:     "mmm d, yyyy",
	longDate:       "mmmm d, yyyy",
	fullDate:       "dddd, mmmm d, yyyy",
	shortTime:      "h:MM TT",
	mediumTime:     "h:MM:ss TT",
	longTime:       "h:MM:ss TT Z",
	isoDate:        "yyyy-mm-dd",
	isoTime:        "HH:MM:ss",
	isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
	isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
dateFormat.i18n = {
	dayNames: [
		"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
	],
	monthNames: [
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
		"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
	]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
	return dateFormat(this, mask, utc);
};