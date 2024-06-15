Code Review Defect List

Reviewer: \_Kiran ShresthaGH Repo: \_shrestha-kiran/ser316-spring24A-kshrest9
ID # Location Problem Description Problem
File and Line Number Category Severity
1 Game.java, Line 6 Missing file banner CG Low
2 Game.java, Line 13 points should be private CG Medium
3 Game.java, Line 74 countCorrectLetters method does not update result variable. FD Blocker
4 Game.java, Line 97 countLetters method could be optimized by using a single loop. CS Minor
5 Game.java, Line 124 Hard-coded string for leaderboard path. CS major
6 Game.java, Line 133 Constructor Game(String fixedWord, String name) does
not use the name parameter. FD Blocker
7 Game.java, Line 168 s etRandomWord method uses a magic number for the random range. CS Major

Category: CS – Code Smell defect. CG – Violation of a coding guideline. Provide the guideline number. FD – Functional defect. Code will not produce the expected result. MD – Miscellaneous defect, for all other defects.
Severity: BR - Blocker, must be fixed asap. MJ – Major, of high importance but not a Blocker LOW – Low.
