//Demo
var paper = document.getElementById('paper');

MAP.drawMap(paper);

var results = $('#outcode-results');
var results = results.find('li.outcode-scores');
var scores = {};


for (var i = 0; i < results.length; i++) {
	var outcode = $(results[i]).find('.outcode-id').text();
	var score = $(results[i]).find('.score').text();
	
	scores[outcode] = score;
}
