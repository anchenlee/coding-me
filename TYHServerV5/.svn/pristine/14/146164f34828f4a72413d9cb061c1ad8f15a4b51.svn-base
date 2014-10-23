
var startTime;

function jumpToSS(ss_url, web_url){
	var iframe = document.createElement("iframe");
	iframe.style.display = "none";
	startTime = new Date().getTime();
	try{
		iframe.src = ss_url; 
		document.body.appendChild(iframe);	
	}catch(err){
		
	}
	
	setTimeout(openUrl(web_url), 2000);
}

function openUrl(web_url){
	var current = new Date().getTime();
	if(current - startTime < 2500){
		if(web_url != null && web_url != ''){
			window.location = web_url;
		}
	}
}