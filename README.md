# chainReact2

Group name: [Chain Reaction ]

Group members: [Mamba Mlibatisi, Goosen Michael, Madida Nonkululeko, Mochoeneng Augustine, Madodonke Chulumanco]

Group mentor: [Timothy Fischer ]

Project title: [Chain Reaction ]

Project page: [https://github.com/AugustMk/chainReact2.git]

Instrustions for use: Press "START GAME" to start the game and then select the number of players from the options listed below the "SELECT PLAYERS" option.The game can be played by two to eight players represented in different colors. The
game is played on a board of 10 x 6. For each table cell, we define a critical mass. The
critical mass is equal to the number of orthogonal neighbors. 4 for regular cells, 3 for
border cells and 2 for corner cells. All cells are initially empty. The red and green
players take turns placing &quot;spheres&quot; of the corresponding color. A red player can only
place (red) balls in squares that are empty or already contain one or more red balls.
When two or more spheres are placed in the same cell, they stack. When a battery is
charged with a number of spheres equal to its critical mass, the battery explodes
immediately. As a result of the explosion, a sphere is added to each orthogonally
adjacent cell, and the initial cell loses as many spheres as its critical mass. Explosions
cause adjacent units to overload, and the chain reaction of explosions continues until
each unit is stable. When a red cell explodes and there are green cells around, the
green cells turn red, other explosion rules still follow. The same rules apply for the other
colors. The winner is the one who removes the orbs of all other players To increase chances of winning, a player must get the corners - getting the corners offers the player an extraordinary chance to part whenever if the other player draws
near to it. Splitting with no different groups mass is of no utilization, you are simply
squandering your opportunity. If a player gets a three joint mass close to different
players mass, that player could get tricked and lose the game and the game is over.

Tools used: We imported andriod annotation.*, nontent.*, graphics.*, util.*, view.*,widget.*,appcompat.*, os.*, contraintlayout.*.

Concepts used: We used Custom classes. In GameLogic.java there is GameLogic class, in Grid.java there is a Grid subclass of Veiw, in MainActivity.java there is a MainActivity subclass of AppCompatActivity, in Particles.java there is a Particles class, in playingPage.java there is a playingPage subclass for AppCompatActivity and in selectPlayersPage.java there is a selectPlayersPage subclass for AppCompatActivity.      

Assumption: From this game a player is expected to place one ball on the corners and the second ball is to cause an explosion,On the edges a third a ball causes an explosion. The balls are expected to move to other positions after the explosion occurs. The starting cell loses as many spheres as its critical mass in the explosion, and one sphere is added to each orthogonally adjacent cell. The chain reaction of explosions continues until each unit is stable because explosions overwhelm nearby units. for example, for two players,  when a red cell explodes nearby green cells, this causes  the green cells to turn red. The winner is the one who removes the orbs of all other players.



