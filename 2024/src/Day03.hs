module Day03 (main, part1, part2) where

--import Data.List
-- import Text.Regex.PCRE ((=~))

main :: IO ()
main = do
    part1
    part2

-- mulRegex :: String
-- mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)"

part1 :: IO ()
part1 = do
    contents <- readFile "input"
    -- print (contents =~ mulRegex :: [String])
    print contents

part2 :: IO ()
part2 = do
    contents <- readFile "input"
    print contents

-- readStr :: String -> String
-- readStr = read