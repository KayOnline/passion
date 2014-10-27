$(function() {

	var s = $("ul > li").filter(function(index, item) {
		var text = $(item).text();
		if (text == 'bb') {
			return false;
		}
	});
	console.info(s);

});
