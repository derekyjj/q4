<!DOCTYPE html>
<html>
<head>
<title>Search Page</title>
</head>
<body>
	<div class="container">		
		<form action="<?php echo $_SERVER['PHP_SELF']?>" method="post">
			<div class="field-container">
				<label>Search: </label>
				<input type="text" id="search-box" name="search">
				<input type="submit" id="search-submit" value="Search">
			</div>
		</form>
	</div>
	<?php 
	if(isset($_POST['search-submit']))
	{
		echo "<div>";
		echo htmlspecialchars($_POST['search']);
		echo "</div>";
	}
	?>
</body>
</html>
