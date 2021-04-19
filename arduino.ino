char incomingData = 0;

int in1 = 7;
int in2 = 6;
int in3 = 5; 
int in4 = 4;

int led = 13;
int frontLed = 3;

bool isFrontLedOn = false;

void setup(){
  Serial.begin(9600);
  
  pinMode (in1, OUTPUT);
  pinMode (in2, OUTPUT);
  pinMode (in3, OUTPUT);
  pinMode (in4, OUTPUT);

  pinMode(led, OUTPUT);
  pinMode(frontLed, OUTPUT);
}

void loop(){

  if(Serial.available() > 0){
    
    incomingData = Serial.read();
    Serial.print(incomingData);
    
    if(incomingData == '4'){
      Serial.print("@@@@ Girando Izquierda...");
      digitalWrite(led, HIGH); 
      digitalWrite (in1, HIGH);
      digitalWrite (in2, LOW);
      digitalWrite (in3, LOW);
      digitalWrite (in4, HIGH); 
    }

    if(incomingData == '3'){
      Serial.print("@@@@ Girando Derecha...");
      digitalWrite(led, HIGH); 
      digitalWrite (in1, LOW);
      digitalWrite (in2, HIGH);
      digitalWrite (in3, HIGH);
      digitalWrite (in4, LOW); 
    }

    if(incomingData == '2'){
      Serial.print("@@@@ Retrocediendo...");
      digitalWrite(led, HIGH); 
      digitalWrite (in1, LOW);
      digitalWrite (in2, HIGH);
      digitalWrite (in3, LOW);
      digitalWrite (in4, HIGH); 
    }

    if(incomingData == '1'){
      Serial.print("@@@@ Avanzando...");
      digitalWrite(led, HIGH); 
      digitalWrite (in1, HIGH);
      digitalWrite (in2, LOW);
      digitalWrite (in3, HIGH);
      digitalWrite (in4, LOW); 
    }

    if(incomingData == '0'){
      Serial.print("@@@@ Alto total.");
      digitalWrite(led, LOW);
      digitalWrite (in1, LOW); 
      digitalWrite (in2, LOW); 
      digitalWrite (in3, LOW); 
      digitalWrite (in4, LOW); 
    }

    if(incomingData == '9'){
      if(isFrontLedOn == true){
        Serial.print("@@@@ Led Off");
        digitalWrite(frontLed, LOW);
        isFrontLedOn = false;
      }else{
        Serial.print("@@@@ Led On");
        digitalWrite(frontLed, HIGH);
        isFrontLedOn = true;
      }
    }
 
  }
  
}
