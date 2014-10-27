
var requiredMajorVersion = 8;
var requiredMinorVersion = 0;
var requiredRevision = 0;

var isIE  = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
var isWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
 
function JSGetSwfVer(i){
	if (navigator.plugins != null && navigator.plugins.length > 0) {
		if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
			var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
            var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
            descArray = flashDescription.split(" ");
            tempArrayMajor = descArray[2].split(".");
            versionMajor = tempArrayMajor[0];
            versionMinor = tempArrayMajor[1];
            if ( descArray[3] != "" ) {
            	tempArrayMinor = descArray[3].split("r");
            } else {
            	tempArrayMinor = descArray[4].split("r");
            }
            versionRevision = tempArrayMinor[1] > 0 ? tempArrayMinor[1] : 0;
          	flashVer = versionMajor + "." + versionMinor + "." + versionRevision;
         } else {
                                flashVer = -1;
                     }
	} else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) {
		flashVer = 4;
	} else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) {
		flashVer = 3;
	} else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) {
    	flashVer = 2;
    } else {                       
    	flashVer = -1;
    }
    return flashVer;
} 
 
function DetectFlashVer(reqMajorVer, reqMinorVer, reqRevision) {
             reqVer = parseFloat(reqMajorVer + "." + reqRevision);
         for (i=25;i>0;i--) {       
                     versionStr = JSGetSwfVer(i);              
                     if (versionStr == -1 ) { 
                                return false;
                     } else if (versionStr != 0) {
                                versionArray      = versionStr.split(".");
                                
                                versionMajor      = versionArray[0];
                                versionMinor      = versionArray[1];
                                versionRevision   = versionArray[2];
                                
                                versionString     = versionMajor + "." + versionRevision;   
                                    versionNum        = parseFloat(versionString);
                                    if ( (versionMajor > reqMajorVer) && (versionNum >= reqVer) ) {
                                                return true;
                                    } else {
                                                return ((versionNum >= reqVer && versionMinor >= reqMinorVer) ? true : false );    
                                    }
                         }
             }          
             return (reqVer ? false : 0.0);
}

function Print2Flash() {
	
	var width="100%"
	var height="100%"
	var align="left"
	var url= "resources/swf/test.swf"
	var name="Print2FlashDoc"
	
	var content = "";
	
	var alternateContent = 'This content requires the Adobe Flash Player. It either has not been installed yet or is prohibited by your browser security settings. Either'
	 	                  + ' <a href="http://www.macromedia.com/go/getflash/">click here to get Flash</a> or loosen your browser security restrictions and then <a href="javascript:document.location.reload()">Refresh</a> this page.'

	 if(isIE && isWin) {  
	     var oeTags = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" '
	     + 'width="'+width+'" height="'+height+'" align="'+align+'" id="'+name+'" '
	     + 'codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version='+requiredMajorVersion+','+requiredMinorVersion+','+requiredRevision+',0">'
	     + '<param name="movie" value="'+url+'" /><param name="quality" value="best" />'
	     + '<param name="allowScriptAccess" value="sameDomain" />'
	     + '<param name="FlashVars" value="extName='+name+'" />'
	     + alternateContent
	     + '<\/object>';
	    content = oeTags;
	   } else if(DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision)) {
	     var oeTags = '<embed src="'+url+'" quality="best" '
	     + 'width="'+width+'" height="'+height+'" align="'+align+'" name="'+name+'" '
	     + 'play="true" '
	     + 'loop="false" '
	     + 'quality="best" '
	     + 'allowScriptAccess="sameDomain" '
	     + 'type="application/x-shockwave-flash" '
	     + 'pluginspage="http://www.macromedia.com/go/getflashplayer" '
	     + 'FlashVars="extName='+name+'">'
	     + '<\/embed>'
	     content = oeTags; 
	   } else {
	 		content = alternateContent;
	   }
	   
	//console.info(content);
	return content;
}
