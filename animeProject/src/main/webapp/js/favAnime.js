$(function() {


	
	
	$("#add-favourite-one-piece").on("click", function() {
		
		var title = $('#anime-title1').text();
		var startYear = $("#start-year1").text();
		var author = $('#author1').text();
		
		var favCharacter = $("#select-character1").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-naruto").on("click", function() {
		
		var title = $('#anime-title2').text();
		var startYear = $("#start-year2").text();
		var author = $('#author2').text();
		
		var favCharacter = $("#select-character2").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-fairy-tail").on("click", function() {
		
		var title = $('#anime-title3').text();
		var startYear = $("#start-year3").text();
		var author = $('#author3').text();
		
		var favCharacter = $("#select-character3").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-boku-no-hero").on("click", function() {
		
		var title = $('#anime-title4').text();
		var startYear = $("#start-year4").text();
		var author = $('#author4').text();
		
		var favCharacter = $("#select-character4").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-one-punch").on("click", function() {
		
		var title = $('#anime-title5').text();
		var startYear = $("#start-year5").text();
		var author = $('#author5').text();
		
		var favCharacter = $("#select-character5").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-nanatsu-no-taizai").on("click", function() {
		
		var title = $('#anime-title6').text();
		var startYear = $("#start-year6").text();
		var author = $('#author6').text();
		
		var favCharacter = $("#select-character6").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-hunter").on("click", function() {
		
		var title = $('#anime-title7').text();
		var startYear = $("#start-year7").text();
		var author = $('#author7').text();
		
		var favCharacter = $("#select-character7").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-bleach").on("click", function() {
		
		var title = $('#anime-title8').text();
		var startYear = $("#start-year8").text();
		var author = $('#author8').text();
		
		var favCharacter = $("#select-character8").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-death-note").on("click", function() {
		
		var title = $('#anime-title9').text();
		var startYear = $("#start-year9").text();
		var author = $('#author9').text();
		
		var favCharacter = $("#select-character9").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-clover").on("click", function() {
		
		var title = $('#anime-title10').text();
		var startYear = $("#start-year10").text();
		var author = $('#author10').text();
		
		var favCharacter = $("#select-character10").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-sword-art").on("click", function() {
		
		var title = $('#anime-title11').text();
		var startYear = $("#start-year11").text();
		var author = $('#author11').text();
		
		var favCharacter = $("#select-character11").val();
		postFavourite(title, author, startYear, favCharacter);
		
	})
	$("#add-favourite-titan").on("click", function() {
		
		var title = $('#anime-title12').text();
		var startYear = $("#start-year12").text();
		var author = $('#author12').text();
		
		var favCharacter = $("#select-character12").val();
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
					
					renderFavourite(response.id, response.title,response.author, response.startYear,response.favouriteCharacter);
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
		console.log(id,title, author, startYear,favCharacter);
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
		removeFavouriteById(favouriteId);
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