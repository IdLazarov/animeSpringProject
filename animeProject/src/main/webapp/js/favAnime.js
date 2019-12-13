$(function() {





	$("#add-favourite-one-piece").on("click", function() {

		var title = $('#anime-title').text();
		var startYear = $(".start-year").text();
		var author = $('.author').text();
		
		var favCharacter = $("#select-character").val();
		
        console.log(title, author, startYear, favCharacter);
        
		postFavourite(title, author, startYear, favCharacter);
		
    })
    


	var postFavourite = function(title, author, startYear, favCharacter) {
		$.ajax({
			method : "POST",
			url : "addFavouriteAnime",
			data : {
				title : title,
				author : author,
				startYear : startYear,
				favouriteCharacter: favCharacter
			}
		}).done(
				function(response) {
					renderFavourite(response.id, response.title,
							response.author, response.startYear,response.favouriteCharacter);
				}).fail(function(response) {
			console.log(response);
		})
	}

	getUserFavourites = function() {
		$.ajax({
			method : "GET",
			url : "getFavouriteAnime"
		}).done(
				function(response) {

					for (var i = 0; i < response.length; i++) {
						var currentFavourite = response[i];
						renderFavourite(response.id, response.title,
							response.author, response.startYear,response.favouriteCharacter);
					}

				}).fail(function(response) {
			console.log(response);
		})
	}

	var renderFavourite = function(id,title, author, startYear,favCharacter) {

		var $template = $('#comment-template').html();
		$template = $($template);

		$template.find('.remove-item').attr('id', id);
		$template.find('.title-info').text(title);
		$template.find('.author-info').text(author);
        $template.find('.start-year-info').text(startYear);
        $template.find('.favourite-character-info').text(favCharacter);

		var $commentsList = $("#comments-list");
		$commentsList.append($template);
	}
    
    

	$(document).on('click', '.remove-item', function() {
		$selectedFavourite = $(this).closest('.list-group-item');
	})

	$("#confirm-delete").on("click", function() {

		var favouriteId = $selectedFavourite.find('.remove-item').attr('id');
		removePostById(favouriteId);
	})

	removeFavouriteById = function(id) {
		$.ajax({
			method : "POST",
			url : "removeMyFavouriteAnime",
			data: {
				id: id
			}
		}).done(function(response) {
			$selectedFavourite.remove();
			$('#confirmDeleteModal').modal('hide');
			
		}).fail(function(response) {
			console.log(response);
		})

	}

	
	getUserFavourites();

})