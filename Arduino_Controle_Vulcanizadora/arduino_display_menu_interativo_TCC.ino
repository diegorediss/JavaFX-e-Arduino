//      Falta implementar termopar aberto
//      Falta implementar resistencia queimada
//      Falta implementar desaceleracao na resistencia

#include <pitches.h>
#include <LiquidCrystal.h>   // Biblioteca do display de cristal liquido
#include <SPI.h>             // Biblioteca de Serial Peripheral Interface (especifica pinos 11, 12, 13 para uso do termopar MAX6675)
#include <Thermocouple.h>    // Biblioteca do termopar MAX6675

// Inicializa a biblioteca do display com os seguintes pinos
LiquidCrystal lcd(8, 9, 4, 5, 6, 7);

// Criar funcao dos botoes


//////////////////////////////// Buzzer de conclusao
int speakerPin = A1;
 
#define c 261
#define d 294
#define e 329
#define f 349
#define g 391
#define gS 415
#define a 440
#define aS 455
#define b 466
#define cH 523
#define cSH 554
#define dH 587
#define dSH 622
#define eH 659
#define fH 698
#define fSH 740
#define gH 784
#define gSH 830
#define aH 880



// Portas analogicas de comando do rele
int portaRele1 = A4;
int portaRele2 = A5;
int portaFalha = A3;

// MAX6675 SPI pinos de chipSelect
#define chipSelectTP1 2                
#define chipSelectTP2 3

// Pinos de SO e SCK do MAX6675
// SO   pino 12  Slave Out
// SCK  pino 13  Serial Clock

// Instanciando o termopar 1 e 2
Thermocouple TP1 = Thermocouple(chipSelectTP1);
Thermocouple TP2 = Thermocouple(chipSelectTP2);


// Unidades do cronometro - iniciar com 4 horaz (padrao)
int unsigned horaz = 00;
int unsigned minutoz = 01;
int unsigned segundoz = 00;
// Unidades do setTempo - iniciar com 4 horas (padrao)
int horas = 00;
int minutos = 01;
int segundos = 00;


// iniciar com 150 graus (padrao)
int temperaturaProg = 35;
int tempReal1 = 0;
int tempReal2 = 0;

int tempoEsgotado = 0;
int flagMostraCronometro = 0;
int flagDelayPID = 0;

// cria sinal cima/baixo
byte flechaCimaBaixo[8] = {
    0b00100,
    0b01010,
    0b10001,
    0b00000,
    0b00000,
    0b10001,
    0b01010,
    0b00100
};
char charFlechaCimaBaixo;

// Estados do menu.
int itemMenuAtual = 0;
int ultimoEstado = 0;




//variável que receberá os dados da porta serial
int dado = 0;
int i = 0;



void setup() {
    Serial.begin(9600);
    // Seta o numero da caracteres
    lcd.begin(16, 2);

    // Cria um caracter/simbolo de flecha pra cima/baixo
    lcd.createChar(0, flechaCimaBaixo);
    char charFlechaCimaBaixo = lcd.write((uint8_t) 0);

    // Pino do buzzer
    pinMode(speakerPin, OUTPUT);
    // Defina os pinos do rele como saida    
    pinMode(portaRele1, OUTPUT);
    pinMode(portaRele2, OUTPUT);
    pinMode(portaFalha, OUTPUT);

    // Imprime no display o titulo da tela
    limpaTitulo();

}

void loop() {

  
  if(Serial.available() > 0){ //verifica se existe comunicação com a porta serial
     
      while ( i < 3) {
      dado = Serial.read();//lê os dados da porta serial
      if(i==0) {
      horas = dado;
      i++;
      }
      if(i==1) {
      minutos = dado;
      i++;
      }
      if(i==2) {
      temperaturaProg = dado;
      i++;
      }
      }
  }
  
  
    //Chama o menu principal.    
    menuPrincipal();
}



///////////////////////////////////////////////////////////////////////////////////////////////////////// Chama menu principal

void menuPrincipal() {

    int estado = 0;
    int x = analogRead(0);
    lcd.setCursor(0, 0);

    // Checa valores analogicos do pino A0 do LCD Keypad Shield
    if (x < 100) { //Direita
        estado = 4;
    } else if (x < 195) { //Cima
        estado = 1;
    } else if (x < 380) { //Baixo
        estado = 2;
    } else if (x < 555) { //Esquerda
        estado = 5;
    } else if (x < 950) { //Select
        estado = 3;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////// Atualiza a tela conforma alterar o menu
    if (estado != ultimoEstado) {
        if (estado == 1) { //Se Cima
            itemMenuAtual = itemMenuAtual - 1;
            if (itemMenuAtual < 0) {
                itemMenuAtual = 3;
            }
            displayMenu(itemMenuAtual);
        } else if (estado == 2) { //Se Baixo
            itemMenuAtual = itemMenuAtual + 1;
            if (itemMenuAtual > 3) {
                itemMenuAtual = 0;
            }
            displayMenu(itemMenuAtual);
        } else if (estado == 3) { //Se Select
            selectMenu(itemMenuAtual);
        }
        //Salva o ultimo estado pra comparar
        ultimoEstado = estado;
    }
    delay(10);
}


//////////////////////////////////////////////////////////////////////////////////////////////////////q  Mostra o menu de opcoes

void displayMenu(int x) {
    switch (x) {
        case 1:
            limpaTitulo();
            lcd.clear();
            lcd.print("> Inserir a");
            lcd.setCursor(0, 1);
            lcd.print("  temperatura");
            break;
        case 2:
            limpaTitulo();
            lcd.clear();
            lcd.print("> Inserir o");
            lcd.setCursor(0, 1);
            lcd.print("  tempo");
            break;
        case 3:
            limpaTitulo();
            lcd.clear();
            lcd.print("> Acompanhar em");
            lcd.setCursor(0, 1);
            lcd.print("  tempo real");
            break;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////  Escreve o titulo na tela

void limpaTitulo() {
    lcd.clear();
    lcd.setCursor(1, 0);
    lcd.print("VULCANIZADORA");
    lcd.setCursor(0, 1);
    lcd.print("Escolha uma opcao");
    lcd.setCursor(0, 1);
}

/////////////////////////////////////////////////////////////////////////////////////////////  Chama as funcoes para cada menu

void selectMenu(int x) {
    switch (x) {
        case 1:
            lcd.print("Temperatura");
            lcd.clear();
            setTemperatura();
            break;
        case 2:
            lcd.clear();
            lcd.print("Tempo");
            setTempo();
            break;
        case 3:
            lcd.clear();
            cronometro();
            break;
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////// Setar a temperatura de vulcanizacao

void setTemperatura() {
    //Estado sempre inicia em 0
    int estado = 0;
    int flagSairSetTemperatura = 0;
    while (flagSairSetTemperatura == 0 || flagSairSetTemperatura == 1) {
        estado = 0;
        printTemperatura();
        int x = analogRead(0);
        lcd.setCursor(0, 0);

        // Checa valores analogicos do pino A0 do LCD Keypad Shield
        if (x < 100) { //Direita
            estado = 4;
        } else if (x < 195) { //Cima
            estado = 1;
        } else if (x < 380) { //Baixo
            estado = 2;
        } else if (x < 555) { //Esquerda
            estado = 5;
        } else if (x < 950) { //Select
            estado = 3;
        }

        if (estado == 5) { //Se esquerda
        } else if (estado == 4) { //Se direita
        } else if (estado == 1) { //Se cima    
            temperaturaProg++;
            delay(300);
        } else if (estado == 2) { //Se baixo
            temperaturaProg--;
            if (temperaturaProg < 0) {
                temperaturaProg = 300;
            }
            delay(300);
        } else if (estado == 3) { //Se Select
            flagSairSetTemperatura++;
            delay(300);
        }
        estado = 0;
    }
    setup();
    displayMenu(1);
}

///////////////////////////////////////////////////////////////////////////////////////////// Printa a temperatura programada no LCD

void printTemperatura() {
    // Inicia o cursor no inicio da segunda linha
    lcd.print("temperatura");
    lcd.setCursor(3, 1);
    lcd.print(temperaturaProg);
    lcd.print((char) 223);
    lcd.print("C");
}

///////////////////////////////////////////////////////////////////////////////////////////// Setar o tempo de vulcanizacao

void setTempo() {
    //Estado inicia sempre em zero
    int estado = 0;
    int flagSair = 0;
    while (flagSair == 0 || flagSair == 1) {
        estado = 0;
        printTempo();
        int x = analogRead(0);
        lcd.setCursor(0, 0);

        if (x < 100) { // Direita
            estado = 4;
        } else if (x < 195) { //Cima
            estado = 1;
        } else if (x < 380) { // Baixo
            estado = 2;
        } else if (x < 555) { // Esquerda
            estado = 5;
        } else if (x < 950) { // Se Select
            estado = estado + 3;
        }

        if (estado == 5) { //Se esquerda
            minutos--;
            if (minutos > 59)
                minutos = 0;
            if (minutos < 0)
                minutos = 59;
            delay(300);
        } else if (estado == 4) { //Se direita
            minutos++;
            if (minutos > 59)
                minutos = 0;
            if (minutos < 0)
                minutos = 59;
            delay(300);
        } else if (estado == 1) { // Se cima   
            horas++;
            if (horas > 23)
                horas = 0;
            if (horas < 0)
                horas = 23;
            delay(300);
        } else if (estado == 2) { // Se baixo
            horas--;
            if (horas > 23)
                horas = 0;
            if (horas < 0)
                horas = 23;
            delay(300);
        } else if (estado == 3) { // Se Select
            flagSair++;
            delay(300);
        }
        estado = 0;
    }

    minutoz = minutos;
    horaz = horas;
    setup();
    displayMenu(1);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////// Printa tempo no LCD

void printTempo() {
    // Inicia cursor no inicio da segunda linha
    lcd.setCursor(0, 1);
    char temperaturaProgSetado[17];
    lcd.print(charFlechaCimaBaixo);
    sprintf(temperaturaProgSetado, "Hrs %02i:%02i Min<>", horas, minutos);
    lcd.print(temperaturaProgSetado);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////  Funcao "cronometro" de tempo

int cronometro() {

    while (horaz > 0 || minutoz > 0 || segundoz >= 0 && tempoEsgotado == 0) {

        // Atualiza temperatura a cada laço do while        
        getTemperatura();

        // Mostra no display o tempo programado
        char hora2f[10];
        sprintf(hora2f, "%02i", horas);
        lcd.print(hora2f);
        lcd.print(":");
        char minuto2f[10];
        sprintf(minuto2f, "%02i", minutos);
        lcd.print(minuto2f);
        lcd.print(":00");
        lcd.setCursor(9, 1);


        // So permite iniciar o cronometro apos atingir a temperatura de trabalho em ambas as resistencias     
        if (tempReal1 >= (temperaturaProg - 1) && tempReal2 >= (temperaturaProg - 1) && flagMostraCronometro == 0) {
            flagMostraCronometro = 1;
        }

        // Funcao semelhante a um PID 
        desaceleraTemperatura();

        ////////////////////////////////////////////////////////////////////////////  Liga ou desliga reles caso abaixo do programado ou com muita diferenca entre rele 1 e 2      
        if (tempoEsgotado == 0) {
            // Instrucao para ligar rele1 se for se tempReal1 for menor que o programado e menor que (rele2)-3
            if (tempReal1 < temperaturaProg && tempReal1 <= (tempReal2 + 3)) {
                digitalWrite(portaRele1, HIGH); //Liga rele 1
            } else {
                digitalWrite(portaRele1, LOW); //Desliga rele 1
            }

            // Instrucao para ligar rele2 se for se tempReal2 for menor que o programado e menor que (rele1)-3
            if (tempReal2 < temperaturaProg && tempReal2 <= (tempReal1 + 3)) {
                digitalWrite(portaRele2, HIGH); //Liga rele 2
            } else {
                digitalWrite(portaRele2, LOW); //Desliga rele 2
            }
        }


        //      delay(250);
        ////////////////////////////////////////////////////////////////////////// Depois de acabado o tempo segue mostrando a temperatura atual
        if (horaz == 0 && minutoz == 0 && segundoz <= 0) {
            melodiaConclusao();
            digitalWrite(portaFalha, HIGH); // Liga led alarme
            digitalWrite(portaRele1, LOW); //Desliga rele 1
            digitalWrite(portaRele2, LOW); //Desliga rele 2
            tempoEsgotado = 1;
            // Segue mostrando temperatura, mesmo com ciclo terminado 
            while (tempoEsgotado == 1) {
                getTemperatura();
                delay(500);
            }
        }


        // Se tiver atingido a temperatura de trabalho mostra no display o tempo real descrescente
        if (flagMostraCronometro == 1) {

            lcd.setCursor(8, 2);
            (horaz < 10) ? lcd.print("0") : NULL; // Se horas for menor que DEZ poe um ZERO a esquerda
            lcd.print(horaz);
            lcd.print(":");
            (minutoz < 10) ? lcd.print("0") : NULL;
            lcd.print(minutoz);
            lcd.print(":");
            (segundoz < 10) ? lcd.print("0") : NULL;
            lcd.print(segundoz);
            lcd.display();
            decrementaTempo();
        }
    }
    flagMostraCronometro = 0;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////// Correcoes e decremento de Hrs-Min-Seg

void decrementaTempo() {
    if (segundoz > 0) {
        segundoz -= 1;
    } else {
        if (minutoz > 0) {
            segundoz = 59;
            minutoz -= 1;
        } else {
            if (horaz > 0) {
                segundoz = 59;
                minutoz = 59;
                horaz -= 1;
            } else {
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////  Retorna temperaturas reais(media de uma amostragem de 10)

void getTemperatura() {
    int amostragem = 5;
    tempReal1 = 0;
    tempReal2 = 0;

    // Inicia a leituira de 4 amostras para apresentar uma media, ou seja, descontar algum tipo de oscilacao que possa ocorrer no termopar
    for (int i = 0; i < amostragem; i++) {
        tempReal1 = TP1.readC() + tempReal1;
        //delay(500);
        tempReal2 = TP2.readC() + tempReal2;
        delay(200);
    }
    // Realiza a media das 2 leituras para cada termopar
    tempReal1 = tempReal1 / amostragem;
    tempReal2 = tempReal2 / amostragem;

    // Mostra no display a temperatura programada e temperaturas reais  
    lcd.setCursor(1, 0);
    lcd.print(temperaturaProg);
    lcd.print((char) 223);
    lcd.print("C");
    lcd.setCursor(0, 1);
    lcd.print(tempReal1);
    lcd.setCursor(4, 1);
    lcd.print(tempReal2);
    lcd.setCursor(8, 0);
}

void desaceleraTemperatura() {
    // Funcao semelhante a um PID
    if (tempReal1 > (temperaturaProg - 4) && tempReal1 < temperaturaProg && flagMostraCronometro == 0) {
        digitalWrite(portaRele1, LOW); //Desiga rele 1
        delay(4000);
    }


    if (tempReal2 > (temperaturaProg - 4) && tempReal2 < temperaturaProg && flagMostraCronometro == 0) {
        digitalWrite(portaRele2, LOW); //Desiga rele 2
        delay(4000);
    }
}







///////////////////////////////////////////////////////////////////////////////////////////////// Musica do fim

void beep (unsigned char speakerPin, int frequencyInHertz, long timeInMilliseconds)
{
    digitalWrite(portaFalha, HIGH);
    //use led to visualize the notes being played
 
    int x;
    long delayAmount = (long)(1000000/frequencyInHertz);
    long loopTime = (long)((timeInMilliseconds*1000)/(delayAmount*2));
    for (x=0;x<loopTime;x++)
    {
        digitalWrite(speakerPin,HIGH);
        delayMicroseconds(delayAmount);
        digitalWrite(speakerPin,LOW);
        delayMicroseconds(delayAmount);
    }
 
    digitalWrite(portaFalha, LOW);
    //set led back to low
 
    delay(20);
    //a little delay to make all notes sound separate
}
 
void melodiaConclusao()
{ 
    beep(speakerPin, a, 500);
    beep(speakerPin, a, 500);
    beep(speakerPin, a, 500);
    beep(speakerPin, f, 350);
    beep(speakerPin, cH, 150);
 
    beep(speakerPin, a, 500);
    beep(speakerPin, f, 350);
    beep(speakerPin, cH, 150);
    beep(speakerPin, a, 1000);
    //first bit
 
    beep(speakerPin, eH, 500);
    beep(speakerPin, eH, 500);
    beep(speakerPin, eH, 500);
    beep(speakerPin, fH, 350);
    beep(speakerPin, cH, 150);
 
    beep(speakerPin, gS, 500);
    beep(speakerPin, f, 350);
    beep(speakerPin, cH, 150);
    beep(speakerPin, a, 1000);
    //second bit...
 
    beep(speakerPin, aH, 500);
    beep(speakerPin, a, 350);
    beep(speakerPin, a, 150);
    beep(speakerPin, aH, 500);
    beep(speakerPin, gSH, 250);
    beep(speakerPin, gH, 250);
 
    beep(speakerPin, fSH, 125);
    beep(speakerPin, fH, 125);
    beep(speakerPin, fSH, 250);
    delay(250);
    beep(speakerPin, aS, 250);
    beep(speakerPin, dSH, 500);
    beep(speakerPin, dH, 250);
    beep(speakerPin, cSH, 250);
    //start of the interesting bit
 
    beep(speakerPin, cH, 125);
    beep(speakerPin, b, 125);
    beep(speakerPin, cH, 250);
    delay(250);
    beep(speakerPin, f, 125);
    beep(speakerPin, gS, 500);
    beep(speakerPin, f, 375);
    beep(speakerPin, a, 125);
 
    beep(speakerPin, cH, 500);
    beep(speakerPin, a, 375);
    beep(speakerPin, cH, 125);
    beep(speakerPin, eH, 1000);
    //more interesting stuff (this doesn't quite get it right somehow)
 
    beep(speakerPin, aH, 500);
    beep(speakerPin, a, 350);
    beep(speakerPin, a, 150);
    beep(speakerPin, aH, 500);
    beep(speakerPin, gSH, 250);
    beep(speakerPin, gH, 250);
 
    beep(speakerPin, fSH, 125);
    beep(speakerPin, fH, 125);
    beep(speakerPin, fSH, 250);
    delay(250);
    beep(speakerPin, aS, 250);
    beep(speakerPin, dSH, 500);
    beep(speakerPin, dH, 250);
    beep(speakerPin, cSH, 250);
    //repeat... repeat
 
    beep(speakerPin, cH, 125);
    beep(speakerPin, b, 125);
    beep(speakerPin, cH, 250);
    delay(250);
    beep(speakerPin, f, 250);
    beep(speakerPin, gS, 500);
    beep(speakerPin, f, 375);
    beep(speakerPin, cH, 125);
 
    beep(speakerPin, a, 500);
    beep(speakerPin, f, 375);
    beep(speakerPin, c, 125);
    beep(speakerPin, a, 1000);
    //and we're done \ó/
}
