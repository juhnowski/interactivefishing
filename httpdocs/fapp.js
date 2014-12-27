/**
* Приложение
*/
FishingApp = function() 
{
    Sim.App.call(this);
}

FishingApp.prototype = new Sim.App();

FishingApp.prototype.init = function(param)
{
    Sim.App.prototype.init.call(this, param);
   
    this.lastX = 0;
	this.lastY = 0;
	this.mouseDown = false;
	this.targetPoints = [];

//********************************************************************    	
    this.controls = [];
 
    var comment = new Control();
    comment.init({ id : Control.ID_COMMENT, icon : "images/target.png" });//images/icons/comment.png
    this.addObject(comment);
    this.controls.push(comment);

    // Create the Control and add it to our sim
    var info = new Control();
    info.init({ id : Control.ID_INFO, icon : "images/target.png" });//images/icons/info.png
    this.addObject(info);
    this.controls.push(info);

    // Create the Control and add it to our sim
    var clock = new Control();
    clock.init({ id : Control.ID_CLOCK, icon : "images/target.png" });//images/icons/clock.png
    this.addObject(clock);
    this.controls.push(clock);
   
    this.layoutControls();
    
    this.selectedControl = null;

//********************************************************************    
    		
    var panorama = new Panorama();
    panorama.init();
    this.addObject(panorama);
    
    var videoFrame = new VideoFrame();
    videoFrame.init();
    this.addObject(videoFrame);
    
    var waterPoint = new WaterPoint();
    waterPoint.init(2,350,25,-100);
    this.addObject(waterPoint);
    
 //   this.createTargetPoints();
    
    this.camera.position.set(0, 0, 0);  
    this.root.rotation.x = Math.PI / 16; 
    this.root.rotation.y = -Math.PI / 2;

}

FishingApp.prototype.handleMouseScroll = function(delta)
{
	var dx = delta*6;

	this.camera.position.z -= dx;
	
	// Clamp to some boundary values
	if (this.camera.position.z < SolarSystemApp.MIN_CAMERA_Z)
		this.camera.position.z = SolarSystemApp.MIN_CAMERA_Z;
	if (this.camera.position.z > SolarSystemApp.MAX_CAMERA_Z)
		this.camera.position.z = SolarSystemApp.MAX_CAMERA_Z;

}

FishingApp.prototype.handleMouseDown = function(x, y)
{
	this.lastX = x; 
	this.lastY = y;
	this.mouseDown = true;
}

FishingApp.prototype.handleMouseUp = function(x, y)
{
	this.lastX = x;
	this.lastY = y;
	this.mouseDown = false;
}

FishingApp.prototype.handleMouseMove = function(x, y)
{

	if (this.mouseDown)
	{
		var dx = x - this.lastX;
		if (Math.abs(dx) > FishingApp.MOUSE_MOVE_TOLERANCE)
		{
			this.root.rotation.y -= (dx * FishingApp.MOUSE_SENSE);
		}
		this.lastX = x;
		
		var dy = y - this.lastY;
		if (Math.abs(dy) > FishingApp.MOUSE_MOVE_TOLERANCE)
		{
			this.root.rotation.x -= (dy * FishingApp.MOUSE_SENSE);
			
			if (this.root.rotation.x > FishingApp.MAX_ROTATION_X)
				this.root.rotation.x = FishingApp.MAX_ROTATION_X;
			
		}
		this.lastY = y;
		
	}	
}

FishingApp.MOUSE_SENSE = 0.001;
FishingApp.MOUSE_MOVE_TOLERANCE = 1;
FishingApp.MAX_ROTATION_X = Math.PI;

startPreloader = function()
{

      // Run through a couple of setup steps, to load and prepare everything
      // before the show can begin..
      var current_preload_step = 0,
      jsonLoader = new THREE.JSONLoader();
      preload_steps = [
         _loadVideo,
         _loadImages,
         _loadOther
      ],
      (function preloadStuff(){
         if (++current_preload_step <= preload_steps.length){
            preload_steps[current_preload_step-1](preloadStuff, jsonLoader)
         } else {
            _wrapUpLoading();
         }
      })();
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
_loadVideo = function(setupStepCompleted, jsonLoader)
{
    setLoadText("Загрузка видео...");
//alert("Загрузка видео");

//============================================================================================================
				var mp = _V_("video1");
				mp.src = 'video/video-2.mp4';
				mp.type = 'video/mp4';


				mp.addEvent
				( 
						'loadstart',
						function() 
						{
						    setLoadText("Загрузка видео: 0%");
   						    setupStepCompleted();
						}
					);

				mp.addEvent
				( 
						'progress',
						function() 
						{
							var howMuchIsDownloaded = mp.bufferedPercent()*100;
							//alert(howMuchIsDownloaded);
							if (howMuchIsDownloaded < 100)
							{
								setLoadText("Загрузка видео: " + String(Math.ceil(howMuchIsDownloaded*2).toPrecision(2)) +"%");
							}
							else
							{
								mp.removeEvent('progress', arguments.callee);
								setupStepCompleted();
							}
						}
					);

//============================================================================================================
   // setupStepCompleted();
}

_loadImages = function(setupStepCompleted, jsonLoader)
{

var images_res = [];

images_res.push('img/3.jpg');
images_res.push('img/2.jpg');
images_res.push('img/bait.gif');
images_res.push('img/c1.png');
images_res.push('img/c2.png');
images_res.push('img/c3.png');
images_res.push('img/c4.png');
images_res.push('img/c5.png');
images_res.push('img/coil.png');
images_res.push('img/graphik.gif');
images_res.push('img/katushka.png');
images_res.push('img/menu.png');
images_res.push('img/pl.gif');
images_res.push('img/pl.jpg');
images_res.push('img/pulse.png');
images_res.push('img/room.png');
images_res.push('img/room_sm.png');
images_res.push('img/sel_bait.png');
images_res.push('img/sel_long.png');
images_res.push('img/sel_medium.png');
images_res.push('img/sel_small.png');
images_res.push('img/startup.png');
images_res.push('img/superbaita.png');
images_res.push('img/superbaitbf.png');
images_res.push('img/tackle.png');
images_res.push('img/tension.png');
			
	var images_res_len = images_res.length;		
    setLoadText("Загрузка изображения: 0 / " + images_res_len);    
    for (var i = 0; i < images_res_len; i++) 
    {
				var img = new Image();
				var rl = this;
				img.src = images_res[i];
				img.addEventListener
				(
				'load',
				 function() { setLoadText("Загрузка изображения: " + i + " / " + images_res_len);
				            },
				 false
				);
    }
    
    setupStepCompleted();     
}

_wrapUpLoading = function(setupStepCompleted, jsonLoader)
{
    setLoadText("Завершение загрузки: 0 / 10");
   // alert("Завершение загрузки");
    $('#progress').remove();
    $('#background').remove();

}

_loadOther = function(setupStepCompleted, jsonLoader)
{
    setLoadText("Загрузка настроек: 0 / 10");
    //alert("Загрузка настроек");
    setupStepCompleted();
}

function setLoadText(txt){
   $('#message').text(txt)
}


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/**
* Объекты приложения
*/

/**
*------------------------------------------------------
* Объект Панорама
*------------------------------------------------------*/
Panorama = function()
{
	Sim.Object.call(this);
}

Panorama.prototype = new Sim.Object();

Panorama.prototype.init = function()
{
    var image = "img/room.png";
    var geometry = new THREE.SphereGeometry(500, 60, 40); //
    var texture = THREE.ImageUtils.loadTexture(image);
    var material = new THREE.MeshBasicMaterial( { map: texture } );
    var mesh = new THREE.Mesh( geometry, material ); 
    
    mesh.scale.x = -1;
    
    this.setObject3D(mesh);
}

/**
*------------------------------------------------------
* Объект Видео
*------------------------------------------------------*/
VideoFrame = function()
{
	Sim.Object.call(this);
}

VideoFrame.prototype = new Sim.Object();

VideoFrame.prototype.init = function()
{
    this.video = document.getElementById( 'video' );
    this.video.addEventListener('ended',videoEnded,false);
/*
    this.video.onended = function(e) {
      alert("on ended");
    }
*/
    this.image = document.createElement( 'canvas' );
	this.image.width = 3000;
	this.image.height = 1212;

	this.imageContext = this.image.getContext( '2d' );
	this.imageContext.fillStyle = '#FFFFFF';//'#000000'
	this.imageContext.fillRect(720,1210, 674, 350 );//720,1210, 674, 350
				
    this.geometry = new THREE.SphereGeometry(500, 60, 40); //
    this.texture = new THREE.Texture( this.image );
	this.texture.minFilter = THREE.LinearFilter;
	this.texture.magFilter = THREE.LinearFilter;
    
    this.material = new THREE.MeshBasicMaterial( { map: this.texture, overdraw: true });
    this.mesh = new THREE.Mesh( this.geometry, this.material ); 
    
    this.mesh.scale.x = -1;
    
    this.setObject3D(this.mesh);
}

VideoFrame.prototype.update = function()
{
	if ( this.video.readyState === this.video.HAVE_ENOUGH_DATA ) 
	{
		this.imageContext.drawImage( this.video, 960, 245);
		if ( this.texture ) this.texture.needsUpdate = true;
	}  
}

/*------------------------------------------------------
* Объект Точка Входа Лески в воду
*------------------------------------------------------*/
WaterPoint = function()
{
	Sim.Object.call(this);
}

WaterPoint.prototype = new Sim.Object();

WaterPoint.prototype.init = function(distance, pos_x, pos_y, pos_z)
{
	var geometry = new THREE.Geometry();
	
	var i, len = 60, twopi = 2 * Math.PI;
	for (i = 0; i <= WaterPoint.N_SEGMENTS; i++)
	{
		var x = distance * Math.cos( i / WaterPoint.N_SEGMENTS * twopi );
		var z = distance * Math.sin( i / WaterPoint.N_SEGMENTS * twopi );
		var vertex = new THREE.Vertex (new THREE.Vector3(x-pos_x, 0-pos_y, z - pos_z));//400,25
		geometry.vertices.push(vertex);
	}
	
	material = new THREE.LineBasicMaterial( { color: 0x1f99bc, opacity: .5, linewidth: 2 } );

	// Create the line
	var line = new THREE.Line( geometry, material );

    // Tell the framework about our object
    this.setObject3D(line);    
}

WaterPoint.N_SEGMENTS = 120;

/*------------------------------------------------------
* Объект Цель для заброса
*------------------------------------------------------*/
TargetPoint = function()
{
	Sim.Object.call(this);
}

TargetPoint.prototype = new Sim.Object();

TargetPoint.prototype.init = function(distance, pos_x, pos_y, pos_z)
{
	var geometry = new THREE.Geometry();
	
	var i, len = 60, twopi = 2 * Math.PI;
	for (i = 0; i <= TargetPoint.N_SEGMENTS; i++)
	{
		var x = distance * Math.cos( i / TargetPoint.N_SEGMENTS * twopi );
		var z = distance * Math.sin( i / TargetPoint.N_SEGMENTS * twopi );
		var vertex = new THREE.Vertex (new THREE.Vector3(x-pos_x, 0-pos_y, z - pos_z));
		geometry.vertices.push(vertex);
	}
	
	material = new THREE.LineBasicMaterial( { color: 0xf87ba1, opacity: .5, linewidth: 2 } );

	// Create the line
	var line = new THREE.Line( geometry, material );

    // Tell the framework about our object
    this.setObject3D(line);    
}

TargetPoint.N_SEGMENTS = 120;

FishingApp.prototype.createTargetPoints = function()
{
	var i, len = FishingApp.paramList.length;

	for (i = 0; i < len; i++)
	{
	    var param = FishingApp.paramList[i];
	    var tPoint = new TargetPoint();

	    tPoint.init(param.distance, param.pos_x, param.pos_y, param.pos_z);
	    this.addObject(tPoint);
	    this.targetPoints.push(tPoint);
	}
}

FishingApp.paramList = [
    { distance: 10, pos_x: 300, pos_y: 25, pos_z: -100},
    { distance: 10, pos_x: 400, pos_y: 25, pos_z: 100},
    { distance: 10, pos_x: 450, pos_y: 25, pos_z: 0}        
];

//********************************************************************    

FishingApp.prototype.layoutControls = function()
{
/*	var i, len = FishingApp.paramList.length;

	for (i = 0; i < len; i++)
	{
	    var param = FishingApp.paramList[i];
    	this.controls[i].setPosition(450,25,0);//param.pos_x, param.pos_y, param.pos_z
    	this.controls[i].subscribe("selected", this, this.onControlSelected)

	}
*/	
	var scale = 2;
	var theta = 0;
	var x = scale * Math.sin(theta);
	var z = scale * Math.cos(theta);
	var y = 0;

	var nControls = this.controls.length;
	var left = (nControls - 1 )/ 2 * -scale;
	
	var i;

	//x = left;
	y = z = 0;
	for (i = 0; i < nControls; i++)
	{
	var param = FishingApp.paramList[i];
		this.controls[i].setPosition(0 - param.pos_x, 0 - param.pos_y, param.pos_z);//x, y, z 0+i*20
		x += scale;
		
		this.controls[i].subscribe("selected", this, this.onControlSelected)
	}
}

FishingApp.prototype.onControlSelected = function(control, selected)
{
	if (control == this.selectedControl)
	{
		if (!selected)
		{
			this.selectedControl = null;
		}
	}
	else
	{
		if (selected)
		{
			if (this.selectedControl)
			{
				this.selectedControl.deselect();
			}
			this.selectedControl = control;
		}
	}
}

FishingApp.prototype.update = function()
{
    TWEEN.update();

    Sim.App.prototype.update.call(this);
}

// Custom Control class
Control = function()
{
	Sim.Object.call(this);
}

Control.prototype = new Sim.Object();

Control.prototype.init = function(param)
{
	this.id = param.id || Control.ID_NONE;
	
	var icon = param.icon || '';
	var map = THREE.ImageUtils.loadTexture(icon);
	
    // Create our Control
    var geometry = new THREE.PlaneGeometry(1, 1, 32, 32);
    var material = new THREE.MeshPhongMaterial( 
    		{ color: 0xffffff, ambient: 0xababab, transparent:true, map:map } );
    var mesh = new THREE.Mesh( geometry, material ); 
    mesh.doubleSided = true;
    mesh.rotation.x = Math.PI / 2;
	mesh.scale.set(10, 10, 10);
    
    // Tell the framework about our object
    this.setObject3D(mesh);
    this.mesh = mesh;
    
    this.selected = false;
    
    // Have the framework show the pointer when over
    this.overCursor = 'pointer';
}

Control.prototype.handleMouseOver = function(x, y)
{
	this.mesh.scale.set(20,20,20);//1.05, 1.05, 1.05
	this.mesh.material.ambient.setRGB(.777,.777,.777);
}

Control.prototype.handleMouseOut = function(x, y)
{
	this.mesh.scale.set(10, 10, 10);
	this.mesh.material.ambient.setRGB(.667, .667, .667);
}

Control.prototype.handleMouseDown = function(x, y, position)
{
	if (!this.selected)
	{
		this.select();
	}
	else
	{
		this.deselect();
	}
}

Control.prototype.select = function()
{
	if (!this.savedposition)
	{
		this.savedposition = this.mesh.position.clone();
	}
	/*
	new TWEEN.Tween(this.mesh.position)
    .to({
        x : 0,
        y : 0,
        z: 2
    	}, 500).start();
	*/
	changeVideo(this.id);
	this.selected = true;
	this.publish("selected", this, true);
}

Control.prototype.deselect = function()
{
/*
	new TWEEN.Tween(this.mesh.position)
    .to({ x: this.savedposition.x, 
    	  y: this.savedposition.y,
    	  z: this.savedposition.z
    	}, 500).start();
*/	
	this.selected = false;
	this.publish("selected", this, false);
}

Control.ID_NONE = 0;
Control.ID_COMMENT = 1;
Control.ID_INFO = 2;
Control.ID_CLOCK = 3;
/*
Control.ID_FAVORITE = 3;
Control.ID_HELP = 4;
*/
//********************************************************************    

