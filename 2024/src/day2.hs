import Data.List
import Data.Ord

part1 = do
    contents <- readFile "input"
    let rows = map (map readInt . words) (lines contents)
    print (length (filter isSafe rows))

part2 = do
    contents <- readFile "input"
    let rows = map (map readInt . words) (lines contents)
    let safe = filter isSafe rows
    let unsafeReports = filter (not . isSafe) rows
    let dampened = filter canDampen unsafeReports

    print (length safe + length dampened)


readInt :: String -> Int
readInt = read

canDampen :: [Int] -> Bool
canDampen report
    | null dampened = False
    | otherwise     = True
    where dampened = filter isSafe (subOneLists report)


isSafe :: [Int] -> Bool
isSafe report
    | maxChange report > 3  = False
    | minChange report == 0 = False
    | otherwise             = allIncreasing report || allDecreasing report

allIncreasing :: [Int] -> Bool
allIncreasing list = list == sort list

allDecreasing :: [Int] -> Bool
allDecreasing list = list == sortBy (comparing Data.Ord.Down) list

maxChange :: [Int] -> Int
maxChange list
    | length list < 2       = 0
    | thisDiff > thatDiff   = thisDiff
    | otherwise             = thatDiff
    where   thisDiff = abs (head list - head (tail list))
            thatDiff = maxChange (tail list)

minChange :: [Int] -> Int
minChange row
    | length row == 2      = thisDiff
    | thisDiff < thatDiff  = thisDiff
    | otherwise            = thatDiff
    where   thisDiff = abs (head row - head (tail row))
            thatDiff = minChange (tail row)

subOneLists :: [a] -> [[a]]
subOneLists [] = []
subOneLists (x : xs) = xs : map (x :) (subOneLists xs)
