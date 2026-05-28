# UBCNET
<p align="center">
    <img height="100" src="img/makeshift_logo.png"/>
</p>

## Step-by-Step guide to use the product:
[Video tutorial/demo link](https://youtu.be/Jg0pPHCoGXA)

1. Download and Install Docker Desktop
2. Go to the official Docker website.
   Select the appropriate version for your system (e.g., "Docker Desktop for Windows for AMD64").
3. Download the installer.
4. Run the downloaded installer file:
5. Click Install and Restart when prompted.
6. Accept the License Agreement.
7. Use the Recommended Settings.
8. Allow the installer to make changes to your device if prompted.
9. Once the installation is complete, restart your system.
10. Launch Docker Desktop.
11. Create a Docker account (or log in if you already have one).
12. Download the latest release from GitHub
13. Unzip the file and remember the folder where it was extracted to
14. Open your system's terminal.
15. Navigate to the project directory (the folder where the zip was extracted)
16. Run the following commands step by step:
    - Build a Docker image:
      `docker build -t ubcnet .`
    - Run the Docker container and expose port 8080:
      `docker run -p 8080:8080 ubcnet`
17. Open a web browser.
18. Navigate to the URL:
    `http://localhost:8080`
If the installation is successful, you will see the project’s website.

## People and roles:

**Project Manager**: Miguel Tampubolon <br>
**Designers**: Fayyad Ahmmed, Pete Tharanamai <br>
**Developers**: Can Kirli, Justin Rao

## Team mission:

<p>As a team, our mission is to foster collaboration, innovation, and learning through the
development of technology that serves the UBC community. We believe in the power of technology
to connect people, solve practical problems, and promote sustainability. Our goal is not only
to deliver a functional and impactful platform but also to cultivate a team culture based on
trust, creativity, and continuous improvement. Together, we aim to grow both as individuals
and as a cohesive unit, while contributing positively to the university environment.</p>

## Problem Statement:

<p>Students and faculty at UBC often struggle to find a centralized platform to buy and sell
items, secure nearby housing, and stay updated on-campus events. Currently, they rely on
fragmented solutions, such as websites like Facebook Marketplace and Instagram, which
do not cater to the specific needs of the UBC community. This leads to inefficient searches
and lost opportunities for interaction and exchange within the university environment.</p>

## Solution (Project Proposal):

<p>Our project is to create a Java-based website that serves as a classified platform
specifically for the University of British Columbia community. This website will feature
three main sections designed to meet the unique needs of UBC students, staff, and faculty:</p>

1.  **Shop**: A platform where users can buy and sell second-hand items related to
    university life. This section focuses on school supplies, textbooks, electronics,
    and other university-relevant products.

2.  **Housing**: A section for local homeowners and students to post about subletting,
    renting, and available accommodations in the UBC vicinity. This will cater to
    students/staff looking for short-term or long-term housing near campus.

3.  **Announcements**: An area for different majors, faculties, and clubs at UBC to
    share news, updates, and events. This will help keep the campus community informed
    about upcoming activities and opportunities.

<p>The platform will be user-friendly, secure, and accessible to all members of the UBC
community, fostering a more connected and resourceful campus life. Overall, our website
will fill the gaps mentioned in the problem statement, and create a popular place for UBC
students to conveniently buy and sell items, find housing opportunities, and stay informed
about campus events, all within a platform designed specifically for their needs.</p>

## Website Specifications:

Design for the homepage:
<p align="center">
    <img width="500" src="img/UBCnet home.png/">
</p>

Design for the announcements page:
<p align="center">
    <img width="500" src="img/UBCnet announcements.png"/>
</p>

Design for the housing page:
<p align="center">
    <img width="500" src="img/UBCnet housing.png"/>
</p>

Design for the shop page:
<p align="center">
    <img width="500" src="img/UBCnet shop.png"/>
</p>

## Requirements:

<p>The UBCNET platform is divided into four main sections: Shop, Housing, Announcements, and the Home page. Requirements should be clearly defined for each section and ensure features are singular, precise, and verifiable. Here are the requirements for each section:</p>

1.  **Shop Requirements**:
    - Users must be able to post items with details like price, description, and image.
    - A contact link for the sellers should be provided (social media, email, etc.).

2. **Housing Requirements**:
    - Users can post rental listings with location, renting price, and contact infromation.
    - Listings must support multimedia, such as images and descriptions

3. **Announcements Requirements**:
    - Users (faculty, club admins) can post events with details such as date, location, and description, but the posts will not be visible to the main site, until manual verification.
    - Users must be able to follow links to events' home pages.

4. **Home Page**
    - Search feature
    - Recent postings for Accouncements, Housing, and Shop

## Architecture:
1. **Client**: Handles user interactions and displays data from the server.
2. **Server**: Manages business logic, interacts with models, and serves data to clients.

---

## Models

### 1. Housing Model
- **Responsibility**: Stores the housing information(title, image URL, description, rental price, location, and the contact infromation).
- **Location**: Server.
- **Communications**:
    - `Housing COntroller` interacts with `HousingmModel` to create, retrieve, and save information.
    - Interfaces with the storage to store the image URL. 

### 2. Shop Model
- **Responsibility**: Stores Shop Item listing information (title, image URL, description, price, condition, and contact information).
- **Location**: Server.
- **Communications**:
    - `ShopController` communicates with `ShopModel` to create, retrieve, and save information.
    - Interacts with storage to store the image URL

### 3. Announcement Model
- **Responsibility**: Stores Announcements/Events data (title and description message).
- **Location**: Server.
- **Communications**:
    - `AnnoucementController` uses `EventModel` to create, retrieve, and save information.
---

## Controllers

### 1.AnnoucementController
- **Responsibility**: Manages the announcement operations like posting
- **Location**: Server.
- **Communications**:
    - Interacts with `AnnoucementModel` to handle creating a new announcement.
    - Read the data from the database and give it to announcement view.

### 2. ShopController
- **Responsibility**: Manages the shop operations like posting and save information.
- **Location**: Server.
- **Communications**:
    - Uses `ShopModel` to handle a new shopmodel.
    - Read the data from the database and give it to shop view
      
### 3. HousingController
- **Responsibility**: Manages Housing listings (create and read).
- **Location**: Server.
- **Communications**:
    - Interacts with `HousingModel` to handle rental listings.
    - Read the data from database and give it to housing view

### 4. IndexController
- **Responsibility**: request the shop, housing, and announcement data from the controller.
- **Location**: Server.
- **Communications**:
    - Communicates with `ShopController`, `AnnouncementController` and `HousingController` retrieve data.
    - Sends appropriate sorting for the view. 

### 5. PostController
- **Responsibility**: Manages every new post and creates appropriate data type for that post.
- **Location**: Server.
- **Communications**:
    - Interact with  `HomePageModel`, `AnnoucementModel`, and `ShopModel` to retrieve aggregated data from each section.
    - Save the data from the user to the database
---

## Views

### 1. Home View
- **Components**:
    - **index.html**: Displays recent announcements, shop items, and house listings.
- **Location**: Client.
- **Communications**:
    - Receives aggregated data from `IndexController` for recent posts.
    - Interact with each type of post `Controller` to show recently added elements to Home Page.
 
### 2. Post View
- **Components**:
    - **post.html**: Displays three types of forms for all types of posts.
    - **script.js**: Handle data when the form is submitted.
- **Location**: Client.
- **Communications**:
    - Receives input data from `index.html` for creating new posts.
    - `script.js` shows a completed or error message to the user when a post is created successfully or an error occurs
    - Interact with `PostController` to update the database.
  

### 3. Marketplace Views
- **Components**:
    - **shop.html**: Displays item listings with all the relevant details.
- **Location**: Client.
- **Communications**:
    - Receives item data from `ShopController` and shows on the page.

### 4. Housing Views
- **Components**:
    - **housing.html**: Displays rental listings with all the relevant details.
- **Location**: Client.
- **Communications**:
     - Receives item data from `HousingController` and shows on the page.

### 5. Announcement Views
- **Components**:
    - **announcement.html**: Displays announcements with all the relevant details.
- **Location**: Client.
- **Communications**:
    - Receives event data from `AnnouncementController` and shows on the page.

---

## Plan:

1. **Coordination and Meetings**
    - The Project Manager Miguel Tampubolon will coordinate the work.
    - Their project management practices will be asking for progress reports on tasks and assignments.
    - Three in-person communcation meetings per week where the agendas are planned by different members each time.
    - Video call meetings if necessary.
2. **Communication Tools**
    - Messaging: Instagram and Discord.
    - Video calling: Discord, Zoom, and Google Meet.
    - These are the commmunication platforms our team has in common, rather than WhatsApp, Messanger, or other platforms that not everyone uses.
4. **Timeline**
    - November 8: Detailed roles and tasks assignments within each subsection of UBCNET.
    - November 9: Understand fully what software development tools to use.
    - November 13: Finished initial code.
    - November 20: Finalize debugging code.
    - November 27: Deploy software and collect feedback from alpha testers who are not members of Filibert-UBCNET
6. **Verification and Testing**
    - Posting Feature:
        * Valid Post Submission- Submit a post with valid information (title, description, price, and image) and confirm that the post appears in the relevant section.
        * Missing Required Fields- Attempt to submit a post without filling required fields (e.g., missing title or price) and verify that the system prompts an error or warning about the missing information.
        * Invalid Data Types- Enter data of incorrect types (e.g., text in the price field) and ensure the system prevents submission with an appropriate error message.
        * Image Upload Validity- Attempt to upload unsupported file types (e.g., .txt) as an image and verify that the system rejects the file with a message indicating supported formats.
        * Post Visibility After Submission-  Submit a post, then check the public page to confirm that the post appears under the correct category and is visible to other users.

---

## Known Bugs:
1. **Search Bar**
    - We have not yet made the search bar work.
2. **Image URL input**
    - Images have to be converted by copying the image address or using the link provided to convert to a direct link before submit into the form.
    - all URLs must end with .type e.g. `.png, .jpg`
    - Most images' direct links work on `bootRun` when run locally, but some images did not work in docker.
3. **Created time Not Working in Docker**
    - For some computers, when a post is created the time created may be shown as UTC time zone.
4. **Negative price value**
    - When creating housing and shop item prices can be input as a negative value. When click submit and error message will pop up but it the post will still ended being created.
5. **New Posts redirect**
    - For all the new posts shown on the Home Page, the user has to click the text in order for the button to work and redirect the user to a new page. Hovering over the box will change the color but clicking the empty space in the box will not redirect the user to the corresponding pages.
  
## Contributions:

1. **Front-End:** Pete Tharanamai
2. **Back-End:** Can Kirli, Justin Rao, Miguel Tampubolon, Fayyad Ahmmed
3. **SPECIFICS:**
    - Controllers:
        * AnnouncementContoller.java, HousingController.java, ShopController.java: Can Kirli, Miguel Tampubolon, Fayyad Ahmmed
        * PostController.java, IndexController.java: Justin Rao, Pete Warot
    - Models: Fayyad Ahmmed, Justin Rao
4. **Resources:** Pete Tharanamai
5. **test:** Can Kirli, Miguel Tampubolon
6. **docker-compose.yml & Dockerfile:** Justin Rao, Pete Tharanamai, Miguel Tampubolon
7. **Video Demonstration:** Can Kirli, Fayyad Ahmmed
    










   
