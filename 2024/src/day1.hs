import Data.List

part1 = do
    contents <- readFile "input"
    let rows = lines contents
    let pairs = map (map readInt . words) (lines contents)
    let firsts = sort (map head pairs)
    let lasts = sort (map last pairs)
    print (totalDiff firsts lasts)

part2 = do
    contents <- readFile "input"
    let rows = lines contents
    let pairs = map (map readInt . words) (lines contents)
    let firsts = map head pairs
    let lasts = map last pairs
    print (simScore firsts lasts)


readInt :: String -> Int
readInt = read

totalDiff :: [Int] -> [Int] -> Int
totalDiff first second
    | null first = 0
    | otherwise  = abs (head first - head second) + totalDiff (tail first) (tail second)

simScore :: [Int] -> [Int] -> Int
simScore first second
    | null first = 0
    | otherwise  = score + simScore (tail first) second
    where score = head first * length (filter (== head first) second)
