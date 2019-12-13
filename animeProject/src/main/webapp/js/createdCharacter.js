$(function() {


	
	

	$("#create-character").on("click", function() {
		
		var name = $('#new-char-name').val();
		var age = $("#new-char-age").val();
		var anime = $('#new-char-anime').val();
		
		var description = $("#description").val();
		postCharacter(name, age, anime, description);
		console.log(name);
	})
	



	var postCharacter = function(name, age, anime, description) {
		
		$.ajax({
			method : "POST",
			url : "addFavouriteCharacter",
			data : {
				name : name,
				age : age,
				anime : anime,
				description: description
			}
		}).done(

				function(response) {
					
					renderCharacter(response.id, response.name,response.age, response.anime,response.description);
				}).fail(function(response) {
			console.log(response);
		})
	}

	getUserCharacters = function() {
		$.ajax({
			method : "GET",
			url : "getFavouriteCharacter"
		}).done(
				function(response) {

					for (var i = 0; i < response.length; i++) {
						var currentCharacter = response[i];
						renderCharacter(response.id, response.name,
							response.age, response.anime,response.description);
					}

				}).fail(function(response) {
			console.log(response);
		})
	}

	var renderCharacter = function(id,name, age, anime, description) {
		console.log(id,name, age, anime, description);
		var $template = $('#character-template').html();
		$template = $($template);

        $template.find('.remove-item').attr('id', id); 
		$template.find('#character-name').text(name);
		$template.find('#character-age').text(age);
        $template.find('#character-anime').text(anime);
        $template.find('#description-ready').text(description);

		var $charactersList = $("#character-template-place");
		$charactersList.append($template);
	}
    
    
    

	$(document).on('click', '.remove-item', function() {
        $selectedCharacter = $(this).closest('.list-group-item');

        console.dir($selectedCharacter);
	})
    
	$("#confirm-delete").on("click", function() {
        // $selectedCharacter.find('#remove-character').attr('id');
        var characterId = $selectedCharacter.find('.remove-item').attr('id');
        console.log($selectedCharacter.find('.remove-item'));
        removeCharacterById(characterId);
       
    })
    
 

	removeCharacterById = function(id) {
		$.ajax({
			method : "POST",
			url : "removeMyFavouriteCharacter",
			data: {
				id: id
			}
		}).done(function(response) {
			$selectedCharacter.remove();
			$('#confirmDeleteModal').modal('hide');
			
		}).fail(function(response) {
			console.log(response);
		})

	}

	
	getUserCharacters();

})