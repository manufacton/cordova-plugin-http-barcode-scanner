/*
 * Copyright (C) 2014-2015 Moonware Studios
 */

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import <CoreGraphics/CoreGraphics.h>
#import <CoreVideo/CoreVideo.h>
#import <CoreMedia/CoreMedia.h>


@interface CameraManager : NSObject
{
    AVCaptureSession *_captureSession;
    AVCaptureDevice *_device;
    
    clock_t _lastCapture;

    BOOL _isCapturing;
    NSTimer *_inactiveTimer;
    
    NSLock *_jpegLock;
    NSData *_jpgData;
    BOOL _newJpgNeeded;
    
    int _selectedFormat;

    NSString* resultString;
 }

@property (nonatomic, retain) AVCaptureSession *captureSession;
@property (nonatomic, retain) AVCaptureDevice *device;
@property (nonatomic) int videoFormat;
@property (nonatomic) NSString *qrCode;

- (void) initCapture;
- (void) deinitCapture;
- (void) startScanning;
- (void) stopScanning;

- (void) resetQr;
- (NSString*) getQrCode;

- (BOOL) getTorch;
- (void) setTorch:(BOOL)enabled;

- (BOOL) isRunning;

- (NSData*) getJpegImage;

- (NSArray*) getVideoFormats;
- (void)setVideoFormat:(int)videoFormat;

@end