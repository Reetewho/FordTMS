function createJobFunction() {
	var varCreateJobUrl = document.getElementById("txtCreateUrl").value;
	var varCreateJobName = document.getElementById("txtCreateCronJobName").value;
	var varCreateJobTime = document.getElementById("txtCreateCronJobTime").value;
	console.log("Show URL : " + varCreateJobUrl);
 	console.log("Show crate cron job name : " + varCreateJobName + " | time :" + varCreateJobTime);
	
	/*
	document.getElementById("txtUrl").value = ' <c:out value = "${myCraeteCronJoburl}"/> ' + 'Send URL TESt'; 
	*/
	
	 $("#showPostUrl").html(valPostCreatURL);

	/* start ajax submission process */
	$.ajax({
	    url: varCreateJobUrl,
	    type: "POST",
	    data: {
			dataCronJobName : varCreateJobName ,
			dataCronJobTime : varCreateJobTime ,
		},
	    success: function(data, textStatus, jqXHR) {
	        alert('Success!');
			console.log("Get value from CreateJobController : " + data);
			console.log("Get value from CreateJobController[textStatus] : " + textStatus);
			console.log("Get value from CreateJobController[jqXHR] : " + jqXHR.status);
	    },
	    error: function(jqXHR, textStatus, errorThrown) {
	        alert('Error occurred!');
	    }
	
	});
}

function stopJobFunction() {
	var varStopJobName = document.getElementById("txtStopCronJobName").value;
	var varStopJobUrl = document.getElementById("txtStopUrl").value;
	console.log("Show URL : " + varStopJobUrl);
 	console.log("Show stop cron job name : " + varStopJobName);
	
	/*
	document.getElementById("txtUrl").value = ' <c:out value = "${myCraeteCronJoburl}"/> ' + 'Send URL TESt'; 
	*/
	
	 $("#showPostUrl").html(valPostStopURL);

	/* start ajax submission process */
	$.ajax({
	    url: varStopJobUrl,
	    type: "POST",
	    data: {
			dataStopJobName : varStopJobName
		},
	    success: function(data, textStatus, jqXHR) {
	        alert('Success!');
			console.log("Get value from CreateJobController : " + data);
			console.log("Get value from CreateJobController[textStatus] : " + textStatus);
			console.log("Get value from CreateJobController[jqXHR] : " + jqXHR.status);
	    },
	    error: function(jqXHR, textStatus, errorThrown) {
	        alert('Error occurred!');
	    }
	
	});
}