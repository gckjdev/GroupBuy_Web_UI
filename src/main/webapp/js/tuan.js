function addfavorite() {
	var url = document.location.href;
	var title = '咕噜团';
	if ( document.all ) { 
		try {
		window.external.addFavorite(url, title);
		}
	
	catch  (e1 ) {
		try { window.external.addToFavoritesBar(url, title);
		}
	catch  (e2 ) {
		alert ('加入收藏失败，请您手工加入。')
		}
	}
	}
	else if  (window.external ) {
	window .sidebar.addPanel(title, url,"");
	}
	
	else {
		alert ('加入收藏失败，请您手工加入。')
	}
	}

function goTopEx(){
		var obj=document.getElementById("goTopBtn");
		function getScrollTop(){
				return document.documentElement.scrollTop;
			}
		function setScrollTop(value){
				document.documentElement.scrollTop=value;
			}	
		window.onscroll=function(){getScrollTop()>0?obj.style.display="":obj.style.display="none";}
		obj.onclick=function(){
			var goTop=setInterval(scrollMove,10);
			function scrollMove(){
					setScrollTop(getScrollTop()/1.1);
					if(getScrollTop()<1)clearInterval(goTop);
				}
		}
	}