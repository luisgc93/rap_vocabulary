/*
 * Parse the data and create a graph with the data.
 */
function parseData(createGraph) {
	Papa.parse("../data/results.csv", {
		download: true,
		complete: function(results) {
			createGraph(results.data);
		}
	});
}

function createGraph(data) {
	var artists = [];
	var count = ["unique words used"];

	for (var i = 1; i < data.length; i++) {
		artists.push(data[i][0]);
		count.push(data[i][1]);
	}

	console.log(artists);
	console.log(count);

	var chart = c3.generate({
		bindto: '#chart',
	    data: {
	        columns: [
	        	count
	        ]
	    },
	    axis: {
	        x: {
	            type: 'category',
	            categories: artists,
	            tick: {
								rotate: 75,
	            	multiline: false,
                	culling: {
                    	max: 100
                	}
            	}
	        }
	    },
	    zoom: {
        	enabled: true
    	},
	    legend: {
	        position: 'right'
	    }
	});
}

parseData(createGraph);
