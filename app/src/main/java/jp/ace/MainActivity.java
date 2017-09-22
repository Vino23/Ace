package jp.ace;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //Widgets
    @BindView(R.id.card1)
    ImageView Card1;
    @BindView(R.id.card2)
    ImageView Card2;

    @BindView(R.id.taparea)
    TextView TapArea;

    @BindView(R.id.userscorehldr)
    TextView tvUserScore;
    @BindView(R.id.aiscorehldr)
    TextView tvAIScore;
    @BindView(R.id.roundvaluehldr)
    TextView tvRoundCount;


    //Variables
    public String CardType,AICardType;
    public int CardValue,AICardValue;
    public int tapcounter=0;
    public int userscore,aiscore;
    public int AIresId;

    TypedArray imgs;

    ArrayList<Integer> numbers;
    ArrayList<String> UserCardHldr;
    ArrayList<String> AICardHldr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Initialize();
    }

    public void Initialize(){

        numbers = new ArrayList<Integer>();
        UserCardHldr = new ArrayList<String>();
        AICardHldr = new ArrayList<String>();

        imgs = getResources().obtainTypedArray(R.array.random_cards);

        //AI Random Value
        for (int i=1; i<=26; i++){
            numbers.add(i);
            Collections.shuffle(numbers);
        }


        TapArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(tapcounter==25){
                if(userscore>aiscore){
                    new MaterialDialog.Builder(MainActivity.this)
                            .titleColorRes(R.color.colorPrimaryDark)
                            .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                            .widgetColorRes(R.color.colorPrimaryDark)
                            .backgroundColorRes(R.color.white)
                            .positiveColorRes(R.color.colorPrimaryDark)
                            .negativeColorRes(R.color.colorPrimaryDark)
                            .title("End Game")
                            .content("You have won the game!")
                            .positiveText("Continue")
                            .negativeText("Retry")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    AICardHldr.clear();
                                    UserCardHldr.clear();
                                    tapcounter=0;
                                    userscore=0;
                                    aiscore=0;
                                    tvAIScore.setText(String.valueOf(aiscore));
                                    tvUserScore.setText(String.valueOf(userscore));
                                    tvRoundCount.setText(String.valueOf(tapcounter));
                                    Card1.setImageDrawable(null);
                                    Card2.setImageDrawable(null);
                                    for (int i=1; i<=26; i++){
                                        numbers.add(i);
                                        Collections.shuffle(numbers);
                                    }
                                }
                            })
                            .cancelable(false)
                            .build()
                            .show();
                }
                else{
                    new MaterialDialog.Builder(MainActivity.this)
                            .titleColorRes(R.color.colorPrimaryDark)
                            .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                            .widgetColorRes(R.color.colorPrimaryDark)
                            .backgroundColorRes(R.color.white)
                            .positiveColorRes(R.color.colorPrimaryDark)
                            .negativeColorRes(R.color.colorPrimaryDark)
                            .title("End Game")
                            .content("You have lost the game!")
                            .positiveText("Continue")
                            .negativeText("Retry")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    AICardHldr.clear();
                                    UserCardHldr.clear();
                                    tapcounter=0;
                                    userscore=0;
                                    aiscore=0;
                                    tvAIScore.setText(String.valueOf(aiscore));
                                    tvUserScore.setText(String.valueOf(userscore));
                                    tvRoundCount.setText(String.valueOf(tapcounter));
                                    Card1.setImageDrawable(null);
                                    Card2.setImageDrawable(null);
                                    for (int i=1; i<=26; i++){
                                        numbers.add(i);
                                        Collections.shuffle(numbers);
                                    }
                                }
                            })
                            .cancelable(false)
                            .build()
                            .show();
                }
            }else{
                    generateUserCard();
                }
            }
        });

    }

    public void generateUserCard(){
        int resID;
        Random rand = new Random();
        int maximumLength = imgs.length();

        int rndInt = rand.nextInt(maximumLength);
        resID = imgs.getResourceId(rndInt, 0);

        String clubace = "icace_of_clubs";
        String club2 = "ic2_of_clubs";
        String club3 = "ic3_of_clubs";
        String club4 = "ic4_of_clubs";
        String club5 = "ic5_of_clubs";
        String club6 = "ic6_of_clubs";
        String club7 = "ic7_of_clubs";
        String club8 = "ic8_of_clubs";
        String club9 = "ic9_of_clubs";
        String club10 = "ic10_of_clubs";
        String clubjack = "icjack_of_clubs2";
        String clubqueen = "icqueen_of_clubs2";
        String clubking = "icking_of_clubs2";

        String diamondsace = "icace_of_diamonds";
        String diamonds2 = "ic2_of_diamonds";
        String diamonds3 = "ic3_of_diamonds";
        String diamonds4 = "ic4_of_diamonds";
        String diamonds5 = "ic5_of_diamonds";
        String diamonds6 = "ic6_of_diamonds";
        String diamonds7 = "ic7_of_diamonds";
        String diamonds8 = "ic8_of_diamonds";
        String diamonds9 = "ic9_of_diamonds";
        String diamonds10 = "ic10_of_diamonds";
        String diamondsjack = "icjack_of_diamonds2";
        String diamondsqueen = "icqueen_of_diamonds2";
        String diamondsking = "icking_of_diamonds2";


        int clubaceID = getResources().getIdentifier(clubace , "drawable", getPackageName());
        int club2ID = getResources().getIdentifier(club2 , "drawable", getPackageName());
        int club3ID = getResources().getIdentifier(club3 , "drawable", getPackageName());
        int club4ID = getResources().getIdentifier(club4 , "drawable", getPackageName());
        int club5ID = getResources().getIdentifier(club5 , "drawable", getPackageName());
        int club6ID = getResources().getIdentifier(club6 , "drawable", getPackageName());
        int club7ID = getResources().getIdentifier(club7 , "drawable", getPackageName());
        int club8ID = getResources().getIdentifier(club8 , "drawable", getPackageName());
        int club9ID = getResources().getIdentifier(club9 , "drawable", getPackageName());
        int club10ID = getResources().getIdentifier(club10 , "drawable", getPackageName());
        int clubJackID = getResources().getIdentifier(clubjack , "drawable", getPackageName());
        int clubQueenID = getResources().getIdentifier(clubqueen , "drawable", getPackageName());
        int clubKingID = getResources().getIdentifier(clubking , "drawable", getPackageName());

        int diamondaceID = getResources().getIdentifier(diamondsace , "drawable", getPackageName());
        int diamond2ID = getResources().getIdentifier(diamonds2 , "drawable", getPackageName());
        int diamond3ID = getResources().getIdentifier(diamonds3 , "drawable", getPackageName());
        int diamond4ID = getResources().getIdentifier(diamonds4 , "drawable", getPackageName());
        int diamond5ID = getResources().getIdentifier(diamonds5 , "drawable", getPackageName());
        int diamond6ID = getResources().getIdentifier(diamonds6 , "drawable", getPackageName());
        int diamond7ID = getResources().getIdentifier(diamonds7 , "drawable", getPackageName());
        int diamond8ID = getResources().getIdentifier(diamonds8 , "drawable", getPackageName());
        int diamond9ID = getResources().getIdentifier(diamonds9 , "drawable", getPackageName());
        int diamond10ID = getResources().getIdentifier(diamonds10 , "drawable", getPackageName());
        int diamondJackID = getResources().getIdentifier(diamondsjack , "drawable", getPackageName());
        int diamondQueenID = getResources().getIdentifier(diamondsqueen , "drawable", getPackageName());
        int diamondKingID = getResources().getIdentifier(diamondsking , "drawable", getPackageName());

        if(clubaceID == resID){
            CardType = "Clubs";
            CardValue = 0;
            if(UserCardHldr.contains("Clubs 1")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 1");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club2ID == resID){
            CardType = "Clubs";
            CardValue = 1;
            if(UserCardHldr.contains("Clubs 2")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 2");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club3ID == resID){
            CardType = "Clubs";
            CardValue = 2;
            if(UserCardHldr.contains("Clubs 3")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 3");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club4ID == resID){
            CardType = "Clubs";
            CardValue = 3;
            if(UserCardHldr.contains("Clubs 4")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 4");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club5ID == resID){
            CardType = "Clubs";
            CardValue = 4;
            if(UserCardHldr.contains("Clubs 5")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 5");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club6ID == resID){
            CardType = "Clubs";
            CardValue = 5;
            if(UserCardHldr.contains("Clubs 6")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 6");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club7ID == resID){
            CardType = "Clubs";
            CardValue = 6;
            if(UserCardHldr.contains("Clubs 7")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 7");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club8ID == resID){
            CardType = "Clubs";
            CardValue = 7;
            if(UserCardHldr.contains("Clubs 8")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 8");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club9ID == resID){
            CardType = "Clubs";
            CardValue = 8;
            if(UserCardHldr.contains("Clubs 9")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 9");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(club10ID == resID){
            CardType = "Clubs";
            CardValue = 9;
            if(UserCardHldr.contains("Clubs 10")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 10");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(clubJackID == resID){
            CardType = "Clubs";
            CardValue = 10;
            if(UserCardHldr.contains("Clubs 11")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 11");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(clubQueenID == resID){
            CardType = "Clubs";
            CardValue = 11;
            if(UserCardHldr.contains("Clubs 12")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 12");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(clubKingID == resID){
            CardType = "Clubs";
            CardValue = 12;
            if(UserCardHldr.contains("Clubs 13")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Clubs 13");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }

        if(diamondaceID == resID){
            CardType = "Diamonds";
            CardValue = 0;
            if(UserCardHldr.contains("Diamonds 1")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 1");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond2ID == resID){
            CardType = "Diamonds";
            CardValue = 1;
            if(UserCardHldr.contains("Diamonds 2")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 2");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond3ID == resID){
            CardType = "Diamonds";
            CardValue = 2;
            if(UserCardHldr.contains("Diamonds 3")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 3");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond4ID == resID){
            CardType = "Diamonds";
            CardValue = 3;
            if(UserCardHldr.contains("Diamonds 4")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 4");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond5ID == resID){
            CardType = "Diamonds";
            CardValue = 4;
            if(UserCardHldr.contains("Diamonds 5")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 5");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond6ID == resID){
            CardType = "Diamonds";
            CardValue = 5;
            if(UserCardHldr.contains("Diamonds 6")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 6");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond7ID == resID){
            CardType = "Diamonds";
            CardValue = 6;
            if(UserCardHldr.contains("Diamonds 7")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 7");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond8ID == resID){
            CardType = "Diamonds";
            CardValue = 7;
            if(UserCardHldr.contains("Diamonds 8")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 8");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond9ID == resID){
            CardType = "Diamonds";
            CardValue = 8;
            if(UserCardHldr.contains("Diamonds 9")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 9");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamond10ID == resID){
            CardType = "Diamonds";
            CardValue = 9;
            if(UserCardHldr.contains("Diamonds 10")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 10");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamondJackID == resID){
            CardType = "Diamonds";
            CardValue = 10;
            if(UserCardHldr.contains("Diamonds 11")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 11");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamondQueenID == resID){
            CardType = "Diamonds";
            CardValue = 11;
            if(UserCardHldr.contains("Diamonds 12")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 12");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
        if(diamondKingID == resID){
            CardType = "Diamonds";
            CardValue = 12;
            if(UserCardHldr.contains("Diamonds 13")){
                generateUserCard();
            }
            else{
                UserCardHldr.add("Diamonds 13");
                Card1.setImageResource(resID);
                generateRandom();
            }
        }
    }

    public void generateRandom(){
        if(tapcounter==0){
            tapcounter++;
            AICardValue = numbers.get(tapcounter);
            tvRoundCount.setText(String.valueOf(tapcounter));
            generateAICard();
        }
        else{
            tapcounter++;
            AICardValue = numbers.get(tapcounter);
            generateAICard();
        }
    }


    public void generateAICard(){
            if (AICardValue == 1) {
                if (AICardHldr.contains("Clubs 1")) {
                    generateRandom();
                } else {
                    AICardValue = 0;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 1");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 2) {
                if (AICardHldr.contains("Clubs 2")) {
                    generateRandom();
                } else {
                    AICardValue = 1;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 2");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 3) {
                if (AICardHldr.contains("Clubs 3")) {
                    generateRandom();
                } else {
                    AICardValue = 2;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 3");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 4) {
                if (AICardHldr.contains("Clubs 4")) {
                    generateRandom();
                } else {
                    AICardValue = 3;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 4");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 5) {
                if (AICardHldr.contains("Clubs 5")) {
                    generateRandom();
                } else {
                    AICardValue = 4;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 5");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 6) {
                if (AICardHldr.contains("Clubs 6")) {
                    generateRandom();
                } else {
                    AICardValue = 5;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 6");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 7) {
                if (AICardHldr.contains("Clubs 7")) {
                    generateRandom();
                } else {
                    AICardValue = 6;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 7");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 8) {
                if (AICardHldr.contains("Clubs 8")) {
                    generateRandom();
                } else {
                    AICardValue = 7;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 8");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 9) {
                if (AICardHldr.contains("Clubs 9")) {
                    generateRandom();
                } else {
                    AICardValue = 8;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 9");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 10) {
                if (AICardHldr.contains("Clubs 10")) {
                    generateRandom();
                } else {
                    AICardValue = 9;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 10");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 11) {
                if (AICardHldr.contains("Clubs 11")) {
                    generateRandom();
                } else {
                    AICardValue = 10;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 11");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 12) {
                if (AICardHldr.contains("Clubs 12")) {
                    generateRandom();
                } else {
                    AICardValue = 11;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 12");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 13) {
                if (AICardHldr.contains("Clubs 13")) {
                    generateRandom();
                } else {
                    AICardValue = 12;
                    AICardType = "Clubs";
                    AICardHldr.add("Clubs 13");
                    AIresId = imgs.getResourceId(AICardValue, 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 14) {
                if (AICardHldr.contains("Diamonds 1")) {
                    generateRandom();
                } else {
                    AICardValue=0;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 1");
                    AIresId = imgs.getResourceId(Integer.valueOf(13), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 15) {
                if (AICardHldr.contains("Diamonds 2")) {
                    generateRandom();
                } else {
                    AICardValue=1;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 2");
                    AIresId = imgs.getResourceId(Integer.valueOf(14), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 16) {
                if (AICardHldr.contains("Diamonds 3")) {
                    generateRandom();
                } else {
                    AICardValue=2;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 3");
                    AIresId = imgs.getResourceId(Integer.valueOf(15), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 17) {
                if (AICardHldr.contains("Diamonds 4")) {
                    generateRandom();
                } else {
                    AICardValue=3;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 4");
                    AIresId = imgs.getResourceId(Integer.valueOf(16), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 18) {
                if (AICardHldr.contains("Diamonds 5")) {
                    generateRandom();
                } else {
                    AICardValue=4;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 5");
                    AIresId = imgs.getResourceId(Integer.valueOf(17), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 19) {
                if (AICardHldr.contains("Diamonds 6")) {
                    generateRandom();
                } else {
                    AICardValue=5;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 6");
                    AIresId = imgs.getResourceId(Integer.valueOf(18), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 20) {
                if (AICardHldr.contains("Diamonds 7")) {
                    generateRandom();
                } else {
                    AICardValue=6;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 7");
                    AIresId = imgs.getResourceId(Integer.valueOf(19), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 21) {
                if (AICardHldr.contains("Diamonds 8")) {
                    generateRandom();
                } else {
                    AICardValue=7;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 8");
                    AIresId = imgs.getResourceId(Integer.valueOf(20), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 22) {
                if (AICardHldr.contains("Diamonds 9")) {
                    generateRandom();
                } else {
                    AICardValue=8;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 9");
                    AIresId = imgs.getResourceId(Integer.valueOf(21), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 23) {
                if (AICardHldr.contains("Diamonds 10")) {
                    generateRandom();
                } else {
                    AICardValue=9;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 10");
                    AIresId = imgs.getResourceId(Integer.valueOf(22), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 24) {
                if (AICardHldr.contains("Diamonds 11")) {
                    generateRandom();
                } else {
                    AICardValue=10;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 11");
                    AIresId = imgs.getResourceId(Integer.valueOf(23), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 25) {
                if (AICardHldr.contains("Diamonds 12")) {
                    generateRandom();
                } else {
                    AICardValue=11;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 12");
                    AIresId = imgs.getResourceId(Integer.valueOf(24), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
            if (AICardValue == 26) {
                if (AICardHldr.contains("Diamonds 13")) {
                    generateRandom();
                } else {
                    AICardValue=12;
                    AICardType = "Diamonds";
                    AICardHldr.add("Diamonds 13");
                    AIresId = imgs.getResourceId(Integer.valueOf(25), 0);
                    Card2.setImageResource(AIresId);
                    compareScore();
                }
            }
    }

    public void compareScore(){
        if(CardValue>AICardValue){
            if(CardType.equals("Diamonds") && AICardType.equals("Clubs")){
                new MaterialDialog.Builder(MainActivity.this)
                        .titleColorRes(R.color.colorPrimaryDark)
                        .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                        .widgetColorRes(R.color.colorPrimaryDark)
                        .backgroundColorRes(R.color.white)
                        .positiveColorRes(R.color.colorPrimaryDark)
                        .negativeColorRes(R.color.colorPrimaryDark)
                        .title("Status")
                        .content("Your Card Won!")
                        .positiveText("Continue")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                userscore = userscore + 1;
                                tvUserScore.setText(String.valueOf(userscore));
                                tvRoundCount.setText(String.valueOf(tapcounter));
                            }
                        })
                        .cancelable(false)
                        .build()
                        .show();
            }else if (CardType.equals("Clubs") && AICardType.equals("Diamonds")){
                new MaterialDialog.Builder(MainActivity.this)
                        .titleColorRes(R.color.colorPrimaryDark)
                        .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                        .widgetColorRes(R.color.colorPrimaryDark)
                        .backgroundColorRes(R.color.white)
                        .positiveColorRes(R.color.colorPrimaryDark)
                        .negativeColorRes(R.color.colorPrimaryDark)
                        .title("Status")
                        .content("Your Card Lost!")
                        .positiveText("Continue")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                aiscore = aiscore + 1;
                                tvAIScore.setText(String.valueOf(aiscore));
                                tvRoundCount.setText(String.valueOf(tapcounter));
                            }
                        })
                        .cancelable(false)
                        .build()
                        .show();
            }
            else if(CardType.equals("Diamonds") && AICardType.equals("Diamonds")){
                new MaterialDialog.Builder(MainActivity.this)
                        .titleColorRes(R.color.colorPrimaryDark)
                        .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                        .widgetColorRes(R.color.colorPrimaryDark)
                        .backgroundColorRes(R.color.white)
                        .positiveColorRes(R.color.colorPrimaryDark)
                        .negativeColorRes(R.color.colorPrimaryDark)
                        .title("Status")
                        .content("Your Card Won!")
                        .positiveText("Continue")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                userscore = userscore + 1;
                                tvUserScore.setText(String.valueOf(userscore));
                                tvRoundCount.setText(String.valueOf(tapcounter));
                            }
                        })
                        .cancelable(false)
                        .build()
                        .show();
            }
            else if(CardType.equals("Clubs") && AICardType.equals("Clubs")){
                new MaterialDialog.Builder(MainActivity.this)
                        .titleColorRes(R.color.colorPrimaryDark)
                        .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                        .widgetColorRes(R.color.colorPrimaryDark)
                        .backgroundColorRes(R.color.white)
                        .positiveColorRes(R.color.colorPrimaryDark)
                        .negativeColorRes(R.color.colorPrimaryDark)
                        .title("Status")
                        .content("Your Card Won!")
                        .positiveText("Continue")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                userscore = userscore + 1;
                                tvUserScore.setText(String.valueOf(userscore));
                                tvRoundCount.setText(String.valueOf(tapcounter));
                            }
                        })
                        .cancelable(false)
                        .build()
                        .show();
            }
        }
         if (CardValue<AICardValue) {
             if (AICardType.equals("Diamonds") && CardType.equals("Clubs")) {
                 new MaterialDialog.Builder(MainActivity.this)
                         .titleColorRes(R.color.colorPrimaryDark)
                         .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                         .widgetColorRes(R.color.colorPrimaryDark)
                         .backgroundColorRes(R.color.white)
                         .positiveColorRes(R.color.colorPrimaryDark)
                         .negativeColorRes(R.color.colorPrimaryDark)
                         .title("Status")
                         .content("Your Card Lost!")
                         .positiveText("Continue")
                         .onPositive(new MaterialDialog.SingleButtonCallback() {
                             @Override
                             public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                 aiscore = aiscore + 1;
                                 tvAIScore.setText(String.valueOf(userscore));
                                 tvRoundCount.setText(String.valueOf(tapcounter));
                             }
                         })
                         .cancelable(false)
                         .build()
                         .show();
             } else if (AICardType.equals("Clubs") && CardType.equals("Diamonds")) {
                 new MaterialDialog.Builder(MainActivity.this)
                         .titleColorRes(R.color.colorPrimaryDark)
                         .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                         .widgetColorRes(R.color.colorPrimaryDark)
                         .backgroundColorRes(R.color.white)
                         .positiveColorRes(R.color.colorPrimaryDark)
                         .negativeColorRes(R.color.colorPrimaryDark)
                         .title("Status")
                         .content("Your Card Won!")
                         .positiveText("Continue")
                         .onPositive(new MaterialDialog.SingleButtonCallback() {
                             @Override
                             public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                 userscore = userscore + 1;
                                 tvUserScore.setText(String.valueOf(aiscore));
                                 tvRoundCount.setText(String.valueOf(tapcounter));
                             }
                         })
                         .cancelable(false)
                         .build()
                         .show();
             } else if (AICardType.equals("Clubs") && CardType.equals("Clubs")) {
                 new MaterialDialog.Builder(MainActivity.this)
                         .titleColorRes(R.color.colorPrimaryDark)
                         .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                         .widgetColorRes(R.color.colorPrimaryDark)
                         .backgroundColorRes(R.color.white)
                         .positiveColorRes(R.color.colorPrimaryDark)
                         .negativeColorRes(R.color.colorPrimaryDark)
                         .title("Status")
                         .content("Your Card Lost!")
                         .positiveText("Continue")
                         .onPositive(new MaterialDialog.SingleButtonCallback() {
                             @Override
                             public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                 aiscore = aiscore + 1;
                                 tvAIScore.setText(String.valueOf(aiscore));
                                 tvRoundCount.setText(String.valueOf(tapcounter));
                             }
                         })
                         .cancelable(false)
                         .build()
                         .show();
             }
             else if (AICardType.equals("Diamonds") && CardType.equals("Diamonds")) {
                 new MaterialDialog.Builder(MainActivity.this)
                         .titleColorRes(R.color.colorPrimaryDark)
                         .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                         .widgetColorRes(R.color.colorPrimaryDark)
                         .backgroundColorRes(R.color.white)
                         .positiveColorRes(R.color.colorPrimaryDark)
                         .negativeColorRes(R.color.colorPrimaryDark)
                         .title("Status")
                         .content("Your Card Lost!")
                         .positiveText("Continue")
                         .onPositive(new MaterialDialog.SingleButtonCallback() {
                             @Override
                             public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                 aiscore = aiscore + 1;
                                 tvAIScore.setText(String.valueOf(aiscore));
                                 tvRoundCount.setText(String.valueOf(tapcounter));
                             }
                         })
                         .cancelable(false)
                         .build()
                         .show();
             }
         }

        else if(CardValue==AICardValue){
            if(CardType.equals("Diamonds") && AICardType.equals("Diamonds")){
                new MaterialDialog.Builder(MainActivity.this)
                        .titleColorRes(R.color.colorPrimaryDark)
                        .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                        .widgetColorRes(R.color.colorPrimaryDark)
                        .backgroundColorRes(R.color.white)
                        .positiveColorRes(R.color.colorPrimaryDark)
                        .negativeColorRes(R.color.colorPrimaryDark)
                        .title("Status")
                        .content("Draw!")
                        .positiveText("Continue")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                tvRoundCount.setText(String.valueOf(tapcounter));
                            }
                        })
                        .cancelable(false)
                        .build()
                        .show();
            }
            if(CardType.equals("Clubs") && AICardType.equals("Clubs")){
                new MaterialDialog.Builder(MainActivity.this)
                        .titleColorRes(R.color.colorPrimaryDark)
                        .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                        .widgetColorRes(R.color.colorPrimaryDark)
                        .backgroundColorRes(R.color.white)
                        .positiveColorRes(R.color.colorPrimaryDark)
                        .negativeColorRes(R.color.colorPrimaryDark)
                        .title("Status")
                        .content("Draw!")
                        .positiveText("Continue")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                tvRoundCount.setText(String.valueOf(tapcounter));
                            }
                        })
                        .cancelable(false)
                        .build()
                        .show();
            }
             if(CardType.equals("Diamonds") && AICardType.equals("Clubs")){
                 new MaterialDialog.Builder(MainActivity.this)
                         .titleColorRes(R.color.colorPrimaryDark)
                         .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                         .widgetColorRes(R.color.colorPrimaryDark)
                         .backgroundColorRes(R.color.white)
                         .positiveColorRes(R.color.colorPrimaryDark)
                         .negativeColorRes(R.color.colorPrimaryDark)
                         .title("Status")
                         .content("Your Card Won!")
                         .positiveText("Continue")
                         .onPositive(new MaterialDialog.SingleButtonCallback() {
                             @Override
                             public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                 userscore = userscore + 1;
                                 tvUserScore.setText(String.valueOf(aiscore));
                                 tvRoundCount.setText(String.valueOf(tapcounter));
                             }
                         })
                         .cancelable(false)
                         .build()
                         .show();
             }
             if(CardType.equals("Clubs") && AICardType.equals("Diamonds")){
                 new MaterialDialog.Builder(MainActivity.this)
                         .titleColorRes(R.color.colorPrimaryDark)
                         .contentColor(getResources().getColor(R.color.colorPrimaryDark))
                         .widgetColorRes(R.color.colorPrimaryDark)
                         .backgroundColorRes(R.color.white)
                         .positiveColorRes(R.color.colorPrimaryDark)
                         .negativeColorRes(R.color.colorPrimaryDark)
                         .title("Status")
                         .content("Your Card Lost!")
                         .positiveText("Continue")
                         .onPositive(new MaterialDialog.SingleButtonCallback() {
                             @Override
                             public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                 aiscore = aiscore + 1;
                                 tvAIScore.setText(String.valueOf(aiscore));
                                 tvRoundCount.setText(String.valueOf(tapcounter));
                             }
                         })
                         .cancelable(false)
                         .build()
                         .show();
             }
        }
    }


}
