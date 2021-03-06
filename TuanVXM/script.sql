USE [master]
GO
/****** Object:  Database [Web24H]    Script Date: 10/21/2017 11:28:52 PM ******/
CREATE DATABASE [Web24H]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Web24H', FILENAME = N'C:\Users\TUANDASE62310\Documents\SQL Server Management Studio\24h\Web24H.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Web24H_log', FILENAME = N'C:\Users\TUANDASE62310\Documents\SQL Server Management Studio\24h\Web24H_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Web24H] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Web24H].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Web24H] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Web24H] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Web24H] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Web24H] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Web24H] SET ARITHABORT OFF 
GO
ALTER DATABASE [Web24H] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Web24H] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Web24H] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Web24H] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Web24H] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Web24H] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Web24H] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Web24H] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Web24H] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Web24H] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Web24H] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Web24H] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Web24H] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Web24H] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Web24H] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Web24H] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Web24H] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Web24H] SET RECOVERY FULL 
GO
ALTER DATABASE [Web24H] SET  MULTI_USER 
GO
ALTER DATABASE [Web24H] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Web24H] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Web24H] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Web24H] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Web24H] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Web24H] SET QUERY_STORE = OFF
GO
USE [Web24H]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [Web24H]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Article](
	[ArticleID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](200) NULL,
	[Headline] [nvarchar](max) NULL,
	[Content] [nvarchar](max) NULL,
	[Thumbnail] [varchar](200) NULL,
	[CategoryID] [int] NULL,
	[CreatorID] [int] NULL,
	[CreatedTime] [datetime] NULL,
	[LastModifierID] [int] NULL,
	[LastModifiedTime] [datetime] NULL,
	[Status] [varchar](45) NULL,
	[LastStatusChangerID] [int] NULL,
	[LastStatusChangedTime] [datetime] NULL,
	[ViewCount] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ArticleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[ArticleID] [int] NULL,
	[CreatorID] [int] NULL,
	[CreatedTime] [datetime] NULL,
	[Content] [nvarchar](200) NULL,
	[Status] [varchar](45) NULL,
	[LastStatusChangerID] [int] NULL,
	[LastStatusChangedTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](45) NULL,
	[Password] [varchar](200) NULL,
	[Name] [nvarchar](45) NULL,
	[Birthday] [date] NULL,
	[Gender] [int] NULL,
	[Address] [nvarchar](200) NULL,
	[Phone] [varchar](20) NULL,
	[Email] [varchar](45) NULL,
	[PeopleIndentityCard] [varchar](45) NULL,
	[PressCard] [varchar](45) NULL,
	[RoleID] [int] NULL,
	[Status] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Article] ON 

INSERT [dbo].[Article] ([ArticleID], [Title], [Headline], [Content], [Thumbnail], [CategoryID], [CreatorID], [CreatedTime], [LastModifierID], [LastModifiedTime], [Status], [LastStatusChangerID], [LastStatusChangedTime], [ViewCount]) VALUES (1, N'updated', N'updated', N'updated', N'updated', 9, 4, CAST(N'2017-10-13T01:55:12.000' AS DateTime), 2, CAST(N'2017-10-13T01:55:55.000' AS DateTime), N'New', 6, CAST(N'2017-10-13T06:27:35.000' AS DateTime), 4)
INSERT [dbo].[Article] ([ArticleID], [Title], [Headline], [Content], [Thumbnail], [CategoryID], [CreatorID], [CreatedTime], [LastModifierID], [LastModifiedTime], [Status], [LastStatusChangerID], [LastStatusChangedTime], [ViewCount]) VALUES (2, N'Tiêu đề', N'Tóm tắt', N'Nội dung', N'Image', 1, 3, CAST(N'2017-10-13T01:55:12.000' AS DateTime), NULL, NULL, N'New', NULL, NULL, 0)
INSERT [dbo].[Article] ([ArticleID], [Title], [Headline], [Content], [Thumbnail], [CategoryID], [CreatorID], [CreatedTime], [LastModifierID], [LastModifiedTime], [Status], [LastStatusChangerID], [LastStatusChangedTime], [ViewCount]) VALUES (4, N'testDTO', N'testDTO', N'testDTO', N'', 4, 4, CAST(N'1970-01-01T07:00:00.000' AS DateTime), 4, CAST(N'1970-01-01T07:00:01.000' AS DateTime), N'Hided', 10, CAST(N'1970-01-01T07:00:00.000' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Article] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (1, N'Tin hàng ngày')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (2, N'Thể thao')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (3, N'Thế giới')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (4, N'Thời trang')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (5, N'An ninh - xã hội')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (6, N'Hi-tech')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (7, N'Tài chính - Địa ốc')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (8, N'Ẩm thực')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (9, N'Sắc đẹp')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (10, N'Showbiz')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (11, N'Giải trí')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (12, N'Nhịp sống trẻ ')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (13, N'Giáo dục')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (14, N'Ô tô')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (15, N'Xe máy')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (16, N'Thị trường - Tiêu dùng')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (17, N'Du lịch')
INSERT [dbo].[Category] ([CategoryID], [Name]) VALUES (18, N'Sức khỏe')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Comment] ON 

INSERT [dbo].[Comment] ([CommentID], [ArticleID], [CreatorID], [CreatedTime], [Content], [Status], [LastStatusChangerID], [LastStatusChangedTime]) VALUES (1, 2, 6, CAST(N'2017-10-13T01:55:12.000' AS DateTime), N'ahihi', N'Available', 2, CAST(N'2017-10-13T01:55:12.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [ArticleID], [CreatorID], [CreatedTime], [Content], [Status], [LastStatusChangerID], [LastStatusChangedTime]) VALUES (2, 1, 6, CAST(N'2017-10-13T01:55:12.000' AS DateTime), N'ahihihihihi', N'Available', NULL, NULL)
INSERT [dbo].[Comment] ([CommentID], [ArticleID], [CreatorID], [CreatedTime], [Content], [Status], [LastStatusChangerID], [LastStatusChangedTime]) VALUES (3, 1, 6, CAST(N'2017-10-13T01:55:12.000' AS DateTime), N'ahihi', N'Available', NULL, NULL)
INSERT [dbo].[Comment] ([CommentID], [ArticleID], [CreatorID], [CreatedTime], [Content], [Status], [LastStatusChangerID], [LastStatusChangedTime]) VALUES (4, 4, 10, CAST(N'1970-01-01T07:00:00.000' AS DateTime), N'Moi tao', N'HideByReader', 2, CAST(N'1970-01-01T07:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Comment] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (1, N'Administrator')
INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (2, N'Editor')
INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (3, N'Journalist')
INSERT [dbo].[Role] ([RoleID], [Name]) VALUES (4, N'Reader')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (1, N'admin', N'admin', N'Tao là admin nè', CAST(N'1985-12-07' AS Date), 1, N'Nhà tao', NULL, NULL, NULL, NULL, 1, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (2, N'editor1', N'editor1', N'Tao là BTV', CAST(N'1992-12-06' AS Date), 1, N'Ở bụi', N'012542546', NULL, N'65727', NULL, 2, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (3, N'editor2', N'editor2', N'Tao cũng là BTV', CAST(N'1979-07-14' AS Date), 1, N'Tao cũng ở bụi', N'045546546', NULL, N'12312354', NULL, 2, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (4, N'journalist1', N'journalist1', N'Tao là nhà báo đời', CAST(N'1993-04-13' AS Date), 2, N'Tao ở bến xe', N'045654654', NULL, N'78978987', N'654656', 3, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (5, N'journalist2', N'journalist2', N'Tao là nhà báo hại', CAST(N'1983-07-25' AS Date), 1, N'Còn tao ở bụi chuối', N'04881421', NULL, N'654654', N'65654654', 3, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (6, N'reader1', N'reader1', N'Tuấn đạp chai', CAST(N'1997-11-11' AS Date), 2, N'Ở dưới gầm cầu', N'012343453', NULL, N'6546546', NULL, 4, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (7, N'reader2', N'reader2', N'Luật bé bỏng', CAST(N'1997-05-21' AS Date), 0, N'K có chỗ ở', N'0126543654', NULL, N'654654', NULL, 4, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (8, N'reader3', N'reader3', N'Trẻ trâu bị ban', CAST(N'1994-04-23' AS Date), 1, N'Trại trẻ nghé mồ côi', N'012654654', NULL, N'7823123621', NULL, 4, N'Banned')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (9, N'reader4', N'reader4', N'Thằng bị khóa mõm', CAST(N'1998-02-14' AS Date), 1, N'Trong chuồng', N'0121234534', NULL, N'6546456', NULL, 4, N'CommentLocked')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (10, N'Testinsert', N'tested', N'Test insert 2', CAST(N'2012-10-13' AS Date), 2, N'Test insert2', N'01234567892', N'Test2', N'1234562', NULL, 4, N'Available')
INSERT [dbo].[User] ([UserID], [Username], [Password], [Name], [Birthday], [Gender], [Address], [Phone], [Email], [PeopleIndentityCard], [PressCard], [RoleID], [Status]) VALUES (11, N'tuanvxm', N'tuanvxm', N'Vo Xuan Minh Tuan', CAST(N'1997-11-12' AS Date), 1, N'QT', N'0945664720', N'tuanvxm@gmail.com', N'197366906', NULL, 4, N'Available')
SET IDENTITY_INSERT [dbo].[User] OFF
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([CreatorID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([LastModifierID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([LastStatusChangerID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([ArticleID])
REFERENCES [dbo].[Article] ([ArticleID])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([CreatorID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([LastStatusChangerID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
/****** Object:  StoredProcedure [dbo].[FindTopViewCountCreatedAfterDate]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[FindTopViewCountCreatedAfterDate] (
	@Top int,
	@Time datetime
) AS
	SELECT TOP (@Top)
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE CreatedTime >= @Time
	ORDER BY ViewCount DESC
GO
/****** Object:  StoredProcedure [dbo].[ProcedureChangeCommentStatus]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureChangeCommentStatus](
	@CommentID int,
	@Status varchar(45),
	@LastStatusChangerID int,
	@LastStatusChangedTime datetime
) AS
	UPDATE Comment
	SET
		[Status] = @Status,
		LastStatusChangerID = @LastStatusChangerID,
		LastStatusChangedTime = @LastStatusChangedTime
	WHERE CommentID = @CommentID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureCreateArticle]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureCreateArticle] (
	@Title nvarchar(200),
	@Headline nvarchar(MAX),
	@Content nvarchar(MAX),
	@Thumbnail varchar(200),
	@CategoryID int,
	@CreatorID int,
	@CreatedTime datetime,
	@Status varchar(45),
	@ViewCount int
) AS
	INSERT INTO Article(Title, Headline, Content, Thumbnail, CategoryID,
		CreatorID, CreatedTime, [Status], ViewCount)
	VALUES (@Title, @Headline, @Content, @Thumbnail, @CategoryID,
		@CreatorID, @CreatedTime, @Status, @ViewCount
	)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureCreateComment]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureCreateComment](
	@ArticleID int,
	@CreatorID int,
	@CreatedTime datetime,
	@Content nvarchar(200),
	@Status varchar(45)
) AS
	INSERT INTO Comment(ArticleID, CreatorID, CreatedTime, Content, [Status])
	VALUES (@ArticleID, @CreatorID, @CreatedTime, @Content, @Status)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindArticleByCategoryIDAndStatus]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindArticleByCategoryIDAndStatus] (
	@CategoryID int,
	@Status varchar(45)
) AS
	SELECT 
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE (CategoryID = @CategoryID AND [Status] = @Status)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindArticleByCreatorID]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindArticleByCreatorID] (
	@CreatorID int
) AS
	SELECT 
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE CreatorID = @CreatorID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindArticleByID]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindArticleByID] (
	@ID int
) AS
	SELECT 
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE ArticleID = @ID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindArticleByStatus]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindArticleByStatus] (
	@Status varchar(45)
) AS
	SELECT 
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE [Status] = @Status
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindArticleByTitle]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindArticleByTitle] (
	@Title nvarchar(200)
) AS
	SELECT 
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE Title like @Title
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindArticleByTitleAndStatus]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindArticleByTitleAndStatus] (
	@Title nvarchar(200),
	@Status varchar(45)
) AS
	SELECT 
		ArticleID,
		Title,
		Headline,
		Content,
		Thumbnail,
		CategoryID,
		CreatorID,
		CreatedTime,
		LastModifierID,
		LastModifiedTime,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime,
		ViewCount
	FROM Article
	WHERE (Title like @Title AND [Status] = @Status)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindCommentByArticleID]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProcedureFindCommentByArticleID] (
	@ArticleID int
) AS
	SELECT 
		CommentID,
		ArticleID,
		CreatorID,
		CreatedTime,
		Content,
		[Status],
		LastStatusChangerID,
		LastStatusChangedTime
	FROM Comment
	WHERE ArticleID = @ArticleID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindUserByRoleID]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindUserByRoleID](
	@RoleID int
) AS
	SELECT 
		UserID,
		Username,
		[Name],
		Birthday,
		Gender,
		[Address],
		Phone,
		Email,
		PeopleIndentityCard,
		PressCard,
		RoleID,
		[Status]
	FROM [User]
	WHERE RoleID = @RoleID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindUserByUsername]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindUserByUsername](
	@Username varchar(45)
) AS
	SELECT 
		UserID,
		Username,
		[Name],
		Birthday,
		Gender,
		[Address],
		Phone,
		Email,
		PeopleIndentityCard,
		PressCard,
		RoleID,
		[Status]
	FROM [User]
	WHERE Username = @Username
GO
/****** Object:  StoredProcedure [dbo].[ProcedureFindUserLikeName]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureFindUserLikeName](
	@Name nvarchar(45)
) AS
	SELECT 
		UserID,
		Username,
		[Name],
		Birthday,
		Gender,
		[Address],
		Phone,
		Email,
		PeopleIndentityCard,
		PressCard,
		RoleID,
		[Status]
	FROM [User]
	WHERE [Name] Like '%'+@Name+'%'
GO
/****** Object:  StoredProcedure [dbo].[ProcedureIncreaseViewCount]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureIncreaseViewCount] (
	@ArticleID int
) AS
	UPDATE Article
	SET
		ViewCount = ViewCount + 1
	WHERE ArticleID = @ArticleID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureInsertUser]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureInsertUser](
	@Username varchar(45),
	@Password varchar(200),
	@Name nvarchar(45),
	@Birthday date,
	@Gender int,
	@Address nvarchar(200),
	@Phone varchar(20),
	@Email varchar(45),
	@PeopleIndentityCard varchar(45),
	@PressCard varchar(45),
	@RoleID int,
	@Status varchar(45)
) AS
	INSERT INTO [User](Username, [Password], [Name], Birthday, Gender, [Address], Phone, Email,
		PeopleIndentityCard, PressCard, RoleID, Status)
	VALUES (@Username, @Password, @Name, @Birthday, @Gender, @Address, @Phone, @Email,
		@PeopleIndentityCard, @PressCard, @RoleID, @Status)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureLoadLoginInformation]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProcedureLoadLoginInformation](
	@Username varchar(45)
) AS
	SELECT 
		UserID as UserID,
		Username as Username,
		[Password] as [Password],
		[Name] as [Name],
		RoleID as RoleID,
		[Status] as [Status]

	FROM [User]
	WHERE [User].Username = @Username
GO
/****** Object:  StoredProcedure [dbo].[ProcedureUpdateArticle]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Update article
CREATE PROCEDURE [dbo].[ProcedureUpdateArticle](
	@ArticleID int,
	@Title nvarchar(200),
	@Headline nvarchar(MAX),
	@Content nvarchar(MAX),
	@Thumbnail varchar(200),
	@CategoryID int,
	@LastModifierID int,
	@LastModifiedTime datetime
) AS
	UPDATE Article
	SET 
		Title = @Title,
		Headline = @Headline,
		Content = @Headline,
		Thumbnail = @Thumbnail,
		CategoryID = @CategoryID,
		LastModifierID = @LastModifierID,
		LastModifiedTime = @LastModifiedTime
	WHERE ArticleID = @ArticleID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureUpdateArticleStatus]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProcedureUpdateArticleStatus](
	@ArticleID int,
	@Status varchar(45),
	@LastStatusChangerID int,
	@LastStatusChangedTime datetime
) AS
	UPDATE Article
	SET
		[Status] = @Status,
		LastStatusChangerID = @LastStatusChangerID,
		LastStatusChangedTime = @LastStatusChangedTime
	WHERE ArticleID = @ArticleID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureUpdatePassword]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureUpdatePassword] (
	@UserID int,
	@OldPassword varchar(200),
	@NewPassword varchar(200)
) AS
	UPDATE [User]
	SET
		[Password] = @NewPassword
	WHERE (UserID = @UserID AND [Password] = @OldPassword)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureUpdateRoleID]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ProcedureUpdateRoleID] (
	@UserID int,
	@RoleID int,
	@PressCard varchar(45)
) AS
	UPDATE [User]
	SET
		RoleID = @RoleID,
		PressCard = @PressCard
	WHERE (UserID = @UserID)
GO
/****** Object:  StoredProcedure [dbo].[ProcedureUpdatetUser]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProcedureUpdatetUser](
	@UserID int,
	@Name nvarchar(45),
	@Birthday date,
	@Gender int,
	@Address nvarchar(200),
	@Phone varchar(20),
	@Email varchar(45),
	@PeopleIndentityCard varchar(45),
	@PressCard varchar(45)
) AS
	UPDATE [User]
	SET 
		[Name] = @Name,
		Birthday = @Birthday,
		Gender = @Gender,
		[Address] = @Address,
		Phone = @Phone,
		Email = @Email,
		PeopleIndentityCard = @PeopleIndentityCard,
		PressCard = @PressCard
	WHERE [User].UserID = @UserID
GO
/****** Object:  StoredProcedure [dbo].[ProcedureUpdateUserStatus]    Script Date: 10/21/2017 11:28:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ProcedureUpdateUserStatus] (
	@UserID int,
	@Status varchar(45)
) AS
	UPDATE [User]
	SET
		[Status] = @Status
	WHERE (UserID = @UserID)
GO
USE [master]
GO
ALTER DATABASE [Web24H] SET  READ_WRITE 
GO
