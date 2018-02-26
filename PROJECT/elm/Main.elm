module Main exposing (..)
import GraphicSVG exposing (..)
import Array exposing (..)
import Maybe exposing (..)
import List exposing (..)

main = gameApp Tick {
                model = myModel, 
                view = myView,
                update = myUpdate
                }


type MyTypes = Tick Float GetKeyState


myModel =
        {
        t = 0,
        grid = [(-50,0), (-40,0), (-30,0), (-20,0), (-10,0), (0,0), (10,0), (20,0), (30,0), (40,0)]
        --NOTE: MAKE GRIDE A LIST OF OF ORDERED PAIRS AND THEN MAP OVER IT WITH cellShape
        }
cell = 
    {
    alive = 0,
    draw = cellShape
    }
cellShape pos = square 10
            |> filled black
            |> move pos

getCell model i j =
    getColumn (getRow model i) model j
getRow model i =
        get i model.grid
getColumn outer model j =
        case outer of
            Just a -> get j a
            Nothing -> Just cell


myView model = collage 300 300 
        [
        --gridplz,
        group (List.map cellShape model.grid)
        ]
whyElm = text "why does this language do the things it does"
        |> fixedwidth
        |> bold
        |> size 14
        |> filled black

gridplz = graphPaper 10


myUpdate msg model = case msg of
    Tick t (getKeyState,p1,p2) -> model