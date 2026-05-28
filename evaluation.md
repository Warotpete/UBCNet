## CPEN-221-2024/project-indis-studbud

### Issues we discovered

1. **GUI Improvements**  
GUI is not user friendly. The text colour and the background colour do not have sufficient contrast, making much of the content hard to read (the issue is most prevalent in the dropdown menus).  
*tags: minor, enhancement*

2. **Installation tutorial clarifications**  
Step 1 of the installation tutorial could be more detailed. It links to an unfamiliar site, but the purpose of using the site is not specified. Moreover, the program worked on our systems without downloading the content in step 1.  
*tags: minor, documentation*

3. **Edit task bug**  
When the edit task window is closed, clicking the edit task button again does not work. However, it does work when clicking "confirm edit" or "cancel edit."  
*tags: minor, bug*

4. **Closing edit settings window terminates program**  
When edit settings window is closed, it terminates the program. However, it does work properly without terminating the program when clicking "confirm edit" or "cancel edit".  
*tags: major, bug*

5. **Save and quit unexpectedly freezes program**  
Clicking save and quit sometimes freezes the program, at which point it has to be forcefully closed using the task manager.  
This issue only affects some computers but is not present in others (in our testing, only one computer out of three experienced this issue).  
*tags: major, bug*

6. **Password appears in plaintext in the terminal**  
The user's password is displayed on the terminal in plaintext after setting the password (major security issue).  
*tags: major, bug*

7. **Adding task fails but doesn't offer an explanation**  
When a task is created outside the specified time window (in settings), the program does not show any noticeable change or alert that explains to the user that something is wrong. The program just stays static and the user has no idea whether the program froze or if there is something wrong with the task they were trying to add. The only indication that something is wrong is the exception messages shown in the terminal.  
*tags: minor, enhancement*

8. **Automatically adds tasks that failed to add without prompting the user**  
When issue 7 (adding task fails but doesn't offer an explanation) occurs and the user changes the settings to accommodate the tasks they were trying to add (by changing the time window), it automatically adds the task that failed to be added before without prompting the user.  
*tags: major, bug*


## Overall process

We approached the testing process systematically by following the project's provided YouTube tutorial and evaluating its features step by step. Our testing methodology included both standard use cases and edge cases to uncover potential issues. For instance, we tested functionalities like adding tasks, editing settings, and closing windows using alternative methods (e.g., clicking the "X" button instead of the provided options). This helped us identify discrepancies between expected and actual behavior.

In addition, we experimented with abnormal inputs, such as inputting extremely large numbers or adding tasks outside of the specified working time, and observed the system's response. These tests highlighted areas where error handling could be improved, as seen in issues like silent task addition failures or unhandled exceptions displayed in the terminal.

While we encountered several minor usability and documentation issues, such as the unclear contrast in the GUI and vague installation tutorial instructions, the major issues we identified, such as freezing during "Save and Quit" on certain computers or plaintext passwords being exposed, represent significant functional and security concerns that would need to be fixed.

Our findings reflect a mix of bugs, enhancements, and documentation improvements that, when addressed, could significantly enhance the usability and reliability of the project.
