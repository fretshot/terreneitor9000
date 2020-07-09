char data = 0;   

void setup(){

  Serial.begin(9600);
  
  pinMode (4, OUTPUT);    // Input4 conectada al pin 4 
  pinMode (5, OUTPUT);    // Input3 conectada al pin 5

  pinMode (8, OUTPUT);    // Input4 conectada al pin 4 
  pinMode (9, OUTPUT);    // Input3 conectada al pin 5
  
}
void loop(){

  if(Serial.available() > 0){
    
    data = Serial.read();     
    Serial.print(data);
    
    if(data == '0'){
      digitalWrite (4, LOW);
      digitalWrite (5, LOW); 

      digitalWrite (8, LOW);
      digitalWrite (9, LOW); 
    }

    if(data == '1'){
      digitalWrite (4, LOW);
      digitalWrite (5, HIGH); 

      digitalWrite (8, LOW);
      digitalWrite (9, HIGH); 
    }

    if(data == '2'){
      digitalWrite (4, HIGH);
      digitalWrite (5, LOW); 

      digitalWrite (8, HIGH);
      digitalWrite (9, LOW); 
    }

    if(data == '3'){
      digitalWrite (4, LOW);
      digitalWrite (5, HIGH); 

      digitalWrite (8, HIGH);
      digitalWrite (9, LOW); 
    }

    if(data == '4'){
      digitalWrite (4, HIGH);
      digitalWrite (5, LOW); 

      digitalWrite (8, LOW);
      digitalWrite (9, HIGH); 
    }
   
  }        

  
}
