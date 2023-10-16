Matthew Szypula

#Java Star Wars Game

- My Game is Star Wars themed, specifically from The Mandalorian TV show. The player is The Mandalorian and has to defeat enemies and prevent them from reaching past the yellow line and his kid.

Custom Features & Game Mechanics:
    - Press spacebar to shoot lasers
    - Player can "get" a lightsaber which allows them to "cut down" any enemy by walking into them (like a star in Super Mario). 
    - The lightsaber does not last forever (and does not stack if more lightsaber collected) and there is a timer bar in the top left corner that tells the user how much time they have left with the lightsaber
    - Game keeps track of tick time and displays it at end on postgame screen so if user wants to keep track of the best time it took them to win.

Win + Lose Conditions: 
    - Lose conditions: an enemy gets past the yellow line or player loses all hp    
    - Win conditions: get 100 points --> Stormtrooper = 1 point, Darktrooper = 2 points (should take around 1-2 minutes)

Assets:
    Splash screen music: https://www.youtube.com/watch?v=bOYdk1UY5o8
    Game music: https://www.youtube.com/watch?v=eoUsbydHJKA
    Splash image modified by me, but used from: data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoGBxQUExYUFBMYGBYZFiIaGRgaGh0bGhodHxoZGhoaGh0cISsiHRwoIBoZIzQjKCwuMjExGiE3PDcwO
    Background image for game made by me
    Stormtrooper made + animated by me + partially AI-generated (DALL-E)
    Darktrooper image file + animated by me: https://www.google.com/url?sa=i&url=https%3A%2F%2Ftwitter.com%2Fchristophsis2%2Fstatus%2F1343396375870939137&psig=AOvVaw3QwiCsmDo6AZLSdc_xJjWI&ust=1682738083829000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCLj23v_Ny_4CFQAAAAAdAAAAABAE
    Mandalorian image file and Grogu + animated by me: https://pbs.twimg.com/media/EJwMmyKWsAELh8I.png
    Medical kit image file: https://static.wikia.nocookie.net/starwars/images/8/87/DarkForcesMedkit.png/revision/latest?cb=20161230210619
    Keyboard keys images: https://dreammix.itch.io/keyboard-keys-for-ui
    Postgame image modified by me: https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/8ab43f85-604c-49e3-92dd-06351347ff2b/d76lrhe-3cd4d172-3be8-4434-8490-d08fc25ae747.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzhhYjQzZjg1LTYwNGMtNDllMy05MmRkLTA2MzUxMzQ3ZmYyYlwvZDc2bHJoZS0zY2Q0ZDE3Mi0zYmU4LTQ0MzQtODQ5MC1kMDhmYzI1YWU3NDcucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.P7Y3waYDZQbCuCfBmuQ-6X4nUu6N7Aa2iFbZ2I3nFEc
    Number image files made by me
    Lightsaber image file made by me
    Timer image files made by me

Other:
    Clip API for music: https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/Clip.html
    Map API for initializing Hashmap with values using Map (used in TimeText.java): https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
    Thread tutorial for waiting before going to postgame screen: https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html
