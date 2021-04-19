function createJobFunction() {
	var varCreateJobUrl = document.getElementById("txtCreateUrl").value;
	var varCreateJobName = document.getElementById("txtCreateCronJobName").value;
	var varCreateLoadID = document.getElementById("txtCreateLoadID").value;
	var varCreateSystemLoadID = document.getElementById("txtCreateSystemLoadID").value;
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
			dataLoadID : varCreateLoadID ,
			dataSystemLoadID : varCreateSystemLoadID ,
			dataCronJobTime : varCreateJobTime
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

function createJobFunction_V2() {
	var varCreateJobUrl_V2 = document.getElementById("txtCreateUrl_V2").value;
	var varCreateJobName_V2 = document.getElementById("txtCreateCronJobName_V2").value;
	var varCreateJobType_V2 = document.getElementById("txtCreateCronJobType_V2").value;
	console.log("Show URL : " + varCreateJobUrl_V2);
 	console.log("Show crate cron job name : " + varCreateJobName_V2 + " | type :" + varCreateJobType_V2);
	
	/*
	document.getElementById("txtUrl").value = ' <c:out value = "${myCraeteCronJoburl}"/> ' + 'Send URL TESt'; 
	*/
	
	 $("#showPostUrl").html(valPostCreatURL_V2);

	/* start ajax submission process */
	$.ajax({
	    url: varCreateJobUrl_V2,
	    type: "POST",
	    data: {
			dataCronJobName : varCreateJobName_V2 ,
			dataCronJobType : varCreateJobType_V2
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