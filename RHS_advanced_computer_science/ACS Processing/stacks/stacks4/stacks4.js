var file, stack;
/*function preload(){
  file = loadStrings("data/stacks4.txt");
  
}*/
function setup() {
  createCanvas(500,500);
  
  textSize(20);
  stack = new Stack();
  /*for(var i = 0; i < 26; i++){
   stack.pushIt(file[i]);
    
  }*/
  
  file = "alligator";
  stack.pushIt(file);
  
  file = "balloon";
  stack.pushIt(file);
  
  file = "camera";
  stack.pushIt(file);
  
  file = "drill";
  stack.pushIt(file);
  
  file = "eagle";
  stack.pushIt(file);
  
  file = "freedom";
  stack.pushIt(file);
  
  noLoop();
}

function draw() {
  background(0);
  fill(255);
  text("Stacks Lab 4", 10,25);
  var temp = "";
 while(!stack.empty){
   temp = temp + " " + stack.popIt(); 
  }
  
  text(temp,10,100, width-10, height);
  textSize(10);
  text("This lab does not look cool. This is what it looks like to print the items out of my stack.",10,150, width-10, height);
}

function Stack(){
 this.stack = [];
 this.size = 0;
 this.pushIt = pushIt;
 this.popIt = popIt;
 this.empty = true;
 this.peek = peek;
}


function pushIt(item){
  this.stack[this.size] = item;
  this.size = this.size + 1;
  this.empty = false;
  console.log("pushed " + item);
}
function popIt(){
  this.size = this.size - 1;
  if(this.size == 0){
   this.empty = true; 
  }
  console.log("popped " +  this.stack[this.size]);
  return this.stack[this.size];
}
function peek(){
  return this.stack[this.size-1];
}