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




