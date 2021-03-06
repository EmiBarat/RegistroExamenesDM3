USE [master]
GO

/****** Object:  Database [DM3_Examenes_EduFis]    Script Date: 2/7/2021 19:02:05 ******/
CREATE DATABASE [DM3_Examenes_EduFis]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DM3_Examenes_EduFis', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DM3_Examenes_EduFis.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DM3_Examenes_EduFis_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DM3_Examenes_EduFis_log.ldf' , SIZE = 4224KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET COMPATIBILITY_LEVEL = 120
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DM3_Examenes_EduFis].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET ARITHABORT OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET  DISABLE_BROKER 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET RECOVERY FULL 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET  MULTI_USER 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET DB_CHAINING OFF 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [DM3_Examenes_EduFis] SET  READ_WRITE 
GO

