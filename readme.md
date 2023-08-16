# A3_CSCI3901
Assignment 3 for CSCI3901

Implement a class called CommitManager that will accept information about commit operations in a version control system and will then report on the status of those commits.

For the sake of this assignment, time and date combinations will be a single integer that is the number of minutes from the time when the version control system was first deployed. We will also omit references to branch names and to comments to simplify this assignment. Last, identifiers for bugs or features are differentiated by the first letter of the string: bug numbers begin with “B-“ while features begin with “F-“.

Your CommitManager class will include the following methods, at a minimum:
- Constructor that accepts no arguments
- void addCommit( String developer, int commitTime, String task, Set<String> commitFiles
  ) throws IllegalArgumentException
- boolean setTimeWindow ( int startTime, int endTime )
- void clearTimeWindow( )
- boolean componentMinimum( int threshold )
- Set<Set<String>> softwareComponents ( )
- Set<String> repetionInBugs ( int threshold )
- Set<String> broadFeatures ( int threshold )
- Set<String> experts ( int threshold )
- List<String> busyClasses ( int limit )
  
The constructor sets up whatever your class needs.

The main reporting methods are softwareComponents, repetitionInBugs, broadFeatures, experts, and busyClasses. These four reports deal with commits made within a given time frame (set by setTimeWindow() and clearTimeWindow() ) and often relate to the components that we detect in the system, which is goverened by componentMinimum( ).